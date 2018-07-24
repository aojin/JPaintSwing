package view;

public enum EventName {
	CHOOSE_SHAPE{
		@Override
		public String toString() {
			return "CHOOSE SHAPE";
		}
	},
	CHOOSE_PRIMARY_COLOR {
		@Override
		public String toString() {
			return "CHOOSE PRIMARY COLOR";
		}
	},
	CHOOSE_SECONDARY_COLOR {
		@Override
		public String toString() {
			return "CHOOSE SECONDARY COLOR";
		}
	},
	CHOOSE_SHADING_TYPE {
		@Override
		public String toString() {
			return "CHOOSE SHADING TYPE";
		}
	},
	CHOOSE_START_POINT_ENDPOINT_MODE {
		@Override
		public String toString() {
			return "CHOOSE START POINT/ENDPOINT MODE";
		}
	},
	UNDO {
		@Override
		public String toString() { return "UNDO"; }
	},
	REDO {
	    @Override
        public String toString() { return "REDO"; }
    },
	COPY {
	    @Override
        public String toString() { return "COPY CURRENT SELECTION"; }
    },
	PASTE {
	    @Override
        public String toString() { return  "PASTE CURRENT SELECTION"; }
    },
	DELETE {
	    @Override
        public String toString(){ return "DELETE CURRENT SELECTION"; }
    }
}
