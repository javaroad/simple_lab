package date;

import java.util.Date;

public enum DateFlag {
	TODAY() {
		@Override
		public String getDate(String str) {
			return DateUtil.toString(new Date(), DateUtil.DEFAULT_SHORT_FORMAT);
		}
	},
	YERSTERDAY() {
		@Override
		public String getDate(String str) {
			return DateUtil.toString(DateUtil.addDays(new Date(), -1),
					DateUtil.DEFAULT_SHORT_FORMAT);
		}
	},
	HISTORY() {
		@Override
		public String getDate(String str) {
			return str;
		}
	};
	public abstract String getDate(String str);
}
