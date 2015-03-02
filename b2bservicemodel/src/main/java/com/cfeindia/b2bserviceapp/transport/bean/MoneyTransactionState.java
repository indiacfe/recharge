package com.cfeindia.b2bserviceapp.transport.bean;

import java.util.EnumSet;
import java.util.Set;

public enum MoneyTransactionState {
	
	PREAUTHORIZATION(true) {
		@Override
		public Set<MoneyTransactionState> nextStates() {
			return EnumSet.of(START, COMPLETED);
		}

		@Override
		public MoneyTransactionState nextState() {
			return START;
		}
	},
	START(false) {
		@Override
		public Set<MoneyTransactionState> nextStates() {
			return EnumSet.of(RECHARGEAPICONFIGURED, COMPLETED);
		}

		@Override
		public MoneyTransactionState nextState() {
			return RECHARGEAPICONFIGURED;
		}
	},
	RECHARGEAPICONFIGURED(false) {
		@Override
		public Set<MoneyTransactionState> nextStates() {
			return EnumSet.of(COMMISSIONCONFIGURED, COMPLETED);
		}

		@Override
		public MoneyTransactionState nextState() {
			return COMMISSIONCONFIGURED;
		}
	},
	COMMISSIONCONFIGURED(false) {
		@Override
		public Set<MoneyTransactionState> nextStates() {
			return EnumSet.of(CHECKEDDUPLICATE, COMPLETED);
		}

		@Override
		public MoneyTransactionState nextState() {
			return CHECKEDDUPLICATE;
		}
	},
	CHECKEDDUPLICATE(false) {
		@Override
		public Set<MoneyTransactionState> nextStates() {
			return EnumSet.of(GENERATETRANSACTIONID, COMPLETED);
		}

		@Override
		public MoneyTransactionState nextState() {
			return GENERATETRANSACTIONID;
		}
	},
	GENERATETRANSACTIONID(false) {
		@Override
		public Set<MoneyTransactionState> nextStates() {
			return EnumSet.of(CHECKAIRTELUSER, COMPLETED);
		}

		@Override
		public MoneyTransactionState nextState() {
			return CHECKAIRTELUSER;
		}
	},
	CHECKAIRTELUSER(false) {
		@Override
		public Set<MoneyTransactionState> nextStates() {
			return EnumSet.of(BALANCEDEDUCTED, COMPLETED);
		}

		@Override
		public MoneyTransactionState nextState() {
			return BALANCEDEDUCTED;
		}
	},
	BALANCEDEDUCTED(false) {
		@Override
		public Set<MoneyTransactionState> nextStates() {
			return EnumSet.of(THIRDPARTYCALLED, COMPLETED);
		}

		@Override
		public MoneyTransactionState nextState() {
			return THIRDPARTYCALLED;
		}
	},
	THIRDPARTYCALLED(false) {
		@Override
		public Set<MoneyTransactionState> nextStates() {
			return EnumSet.of(BALANCEMANAGED, COMPLETED);
		}

		@Override
		public MoneyTransactionState nextState() {
			return BALANCEMANAGED;
		}
	},
	BALANCEMANAGED(false) {
		@Override
		public Set<MoneyTransactionState> nextStates() {
			return EnumSet.of(COMPLETED);
		}

		@Override
		public MoneyTransactionState nextState() {
			return COMPLETED;
		}
	},
	COMPLETED(false) {
		@Override
		public Set<MoneyTransactionState> nextStates() {
			return EnumSet.of(COMPLETED);
		}

		@Override
		public MoneyTransactionState nextState() {
			return COMPLETED;
		}
	};
	private final boolean editable;

	private MoneyTransactionState(final boolean editable) {
		this.editable = editable;
	}

	public abstract Set<MoneyTransactionState> nextStates();

	public abstract MoneyTransactionState nextState();

	public boolean isEditable() {
		return editable;
	}

	/**
	 * Check if a state is a valid next state, and return it if so.
	 * 
	 * @param state
	 *            to check
	 * @return the state if it's valid.
	 */
	public MoneyTransactionState validateNextState(final MoneyTransactionState state) {
		if (nextStates().contains(state)) {
			return state;
		} else {
			throw new IllegalStateException("Cannot transition from state "
					+ this + " to " + state);
		}
	}


}
