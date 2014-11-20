import sys,os,time
from fabric.api import *
import fabric

netifs = {}
now = time.strftime('%Y%m%d-%H%M%S',time.localtime(time.time()))


def host(str=''):
    if str == '-':
        list = _to_list(sys.stdin)
    else:
        list =  str.split('\n')
    env.hosts = _to_hosts(list)
    env.passwords = _to_passwords(list)
    netifs = _to_netifs(list)
    print env.hosts



def _to_list(handle):
    list = []
    while True:
        line = handle.readline().strip()
        if line:
            list.append(line)
        else:
            return list


def _to_hosts(list):
    hosts = []
    for line in list:
        kvp = line.split('|')
        hosts.append(kvp[1])
    return hosts


def _to_passwords(list):
    passwords = {}
    for line in list:
        kvp = line.split('|')
        passwords[kvp[1]] = kvp[2]
    return passwords
        

def _to_netifs(list):
    passwords = {}
    for line in list:
        kvp = line.split('|')
        netifs[kvp[1]] = kvp[0]
    return netifs


def setpubkey():
    put("~/.ssh/id_rsa.pub","/tmp/")
    run("chattr -ai /root/.ssh/authorized_keys")
    run("cat /tmp/id_rsa.pub >> /root/.ssh/authorized_keys")
    run("chmod 600 /root/.ssh/authorized_keys")


def chpasswd(pwd):
    local("../chgpwd.sh %s %s " % (env.host,pwd))

def stop_transcode():
    clear_tmp()
    run("/uts/transmanager-run/transmanager.sh stop",pty=False)

def clear_tmp():
    run("rm -rf /tmp/winstone")
    run("rm -rf /tmp/winstone.tmp")

def clear_download():
    run("rm -rf /data/transcoder/download-cache/*")

def start_transcode():
    clear_tmp()
    run("mkdir -p /data/transcoder/")
    run("/uts/transmanager-run/transmanager.sh start",pty=False)

def version():
    local("echo `curl http://%s:9002/version 2>/dev/null`  " % env.host)

def rsync_fonts():
    fabric.contrib.project.rsync_project(local_dir="/etc/fonts/conf.avail/51-local.conf",remote_dir="/etc/fonts/conf.avail/",extra_opts="--links")    
    run('rm -rf  /usr/share/fonts/uts')
    #run('mkdir -p  /data/fonts')
    #with settings(warn_only=True):
    #    run('mv /usr/share/fonts/uts/*  /data/fonts/)')
    fabric.contrib.project.rsync_project(local_dir="/data1/tar/",remote_dir="/data")
    run("cd /data && tar -zxvf fonts.tar.gz")
    fabric.contrib.project.rsync_project(local_dir="/etc/fonts/fonts.conf",remote_dir="/etc/fonts/",extra_opts="--links")
#------------mission----

def rsync_mission():
    fabric.contrib.project.rsync_project(local_dir="./project/mission/transmanager-run",remote_dir="/uts/",extra_opts="--links")

#------------mission end----


def rsync_yum_repo():
    fabric.contrib.project.rsync_project(local_dir="./project/yum.repos.d/letv-test.repo",remote_dir="/etc/yum.repos.d",extra_opts="--links")


def rsync_transmanager():
    fabric.contrib.project.rsync_project(local_dir="./project/transmanager-run",remote_dir="/uts/",extra_opts="--links")

def rsync_transcoder(server_type):
    fabric.contrib.project.rsync_project(local_dir="./project/%s/transcoder" % server_type ,remote_dir="/uts/",extra_opts="--links")

def rsync_sh(server_type):
    fabric.contrib.project.rsync_project(local_dir="./project/%s/transcoder/sh" % server_type ,remote_dir="/uts/transcoder/",extra_opts="--links")

def rsync_py(server_type):
    fabric.contrib.project.rsync_project(local_dir="./project/%s/transcoder/py" % server_type ,remote_dir="/uts/transcoder/",extra_opts="--links")

def disk_use():
    #run("df -h")
    run("ls /data/transcoder |wc -l")

def init_env():
    run('echo "/uts/transmanager-run/transmanager.sh start &" >> /etc/rc.local')
    run('curl http://220.181.153.218/centos/letv-test.sh|sh')
    
    run('mkdir -p  /letv/data ')
    run('mkdir -p /data/transcoder')
    run('test -L /tmp || (mv  /tmp /letv/data/ && ln -s /letv/data/tmp /)')
    run('test -L /data || (mv  /data/* /letv/data/ && rm -rf /data && ln -s /letv/data /)')
    run('yum install -y transcoder jdk-1.6.0 poster')

def restart():
    run("/uts/transmanager-run/transmanager.sh restart",pty=False)

def install_transcoder(server_type):
    rsync_yum_repo()
    init_env()
    rsync_transcoder(server_type)
    rsync_transmanager()


def install_zlib():
    run('rm -rf /usr/lib64/zlib/lib')
    run('echo "" > /etc/ld.so.conf')
    run('yum clean all')
    run('ldconfig -v|grep libz')
    run('yum clean all')
    rsync_zlib()
    updateLibConf()
    ist_zlib()
    check_zlib()

def rsync_zlib():
    fabric.contrib.project.rsync_project(local_dir="./project/zlib",remote_dir="/uts/",extra_opts="--links")

def updateLibConf():
    run('echo "/usr/lib64/zlib/lib" >> /etc/ld.so.conf ')

def ist_zlib():
    run('tar -zxvf /uts/zlib/zlib-1.2.5.tar.gz -C /uts/zlib/')
    run('cd /uts/zlib/zlib-1.2.5 && ./configure --shared --prefix=/usr/lib64/zlib && make && make install')

def check_zlib():
    run('ldconfig -p|grep libz ') 

def check_mediainfo():
    run('mediainfo')

#def install_zlib000():
#    fabric.contrib.project.rsync_project(local_dir="./zlib/libzen.so.0.0.0",remote_dir="/usr/lib64/",extra_opts="--links")
#    run('cd /usr/lib64/ && ln -s libzen.so.0.0.0 libzen.so.0')

def re_install(server_type):
     #stop_transcode()
     run('echo "" > /etc/ld.so.conf ')
     run('yum clean all')
     run('ldconfig -v|grep libz')
     run('yum clean all')
     run('yum install -y transcoder')
     run('yum install -y poster')
     rsync_transcoder(server_type)
     rsync_transmanager()
     install_zlib()
     start_transcode()

def install_poster():
    run('echo "" > /etc/ld.so.conf ')
    run('yum clean all')
    run('ldconfig -v|grep libz')
    run('yum clean all')
    run('yum install -y poster')
    run('echo "/usr/lib64/zlib/lib" >> /etc/ld.so.conf ')
def ldconfig():
    run('echo "/usr/lib64/zlib/lib" > /etc/ld.so.conf ')
    run('ldconfig -v|grep libz')
