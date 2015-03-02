package com.cfeindia.b2bserviceapp.transport.bean;

import java.util.EnumSet;
import java.util.Set;

public enum TransactionState {
	/**
	 * Start of the transaction.
	 */
	PREAUTHORIZATION(true) {
		@Override
		public Set<TransactionState> nextStates() {
			return EnumSet.of(START, COMPLETED);
		}

		@Override
		public TransactionState nextState() {
			return START;
		}
	},
	START(false) {
		@Override
		public Set<TransactionState> nextStates() {
			return EnumSet.of(RECHARGEAPICONFIGURED, COMPLETED);
		}

		@Override
		public TransactionState nextState() {
			return RECHARGEAPICONFIGURED;
		}
	},
	RECHARGEAPICONFIGURED(false) {
		@Override
		public Set<TransactionState> nextStates() {
			return EnumSet.of(COMMISSIONCONFIGURED, COMPLETED);
		}

		@Override
		public TransactionState nextState() {
			return COMMISSIONCONFIGURED;
		}
	},
	COMMISSIONCONFIGURED(false) {
		@Override
		public Set<TransactionState> nextStates() {
			return EnumSet.of(CHECKEDDUPLICATE, COMPLETED);
		}

		@Override
		public TransactionState nextState() {
			return CHECKEDDUPLICATE;
		}
	},
	CHECKEDDUPLICATE(false) {
		@Override
		public Set<TransactionState> nextStates() {
			return EnumSet.of(GENERATETRANSACTIONID, COMPLETED);
		}

		@Override
		public TransactionState nextState() {
			return GENERATETRANSACTIONID;
		}
	},
	GENERATETRANSACTIONID(false) {
		@Override
		public Set<TransactionState> nextStates() {
			return EnumSet.of(CHECKAIRTELUSER, COMPLETED);
		}

		@Override
		public TransactionState nextState() {
			return CHECKAIRTELUSER;
		}
	},
	CHECKAIRTELUSER(false) {
		@Override
		public Set<TransactionState> nextStates() {
			return EnumSet.of(BALANCEDEDUCTED, COMPLETED);
		}

		@Override
		public TransactionState nextState() {
			return BALANCEDEDUCTED;
		}
	},
	BALANCEDEDUCTED(false) {
		@Override
		public Set<TransactionState> nextStates() {
			return EnumSet.of(THIRDPARTYCALLED, COMPLETED);
		}

		@Override
		public TransactionState nextState() {
			return THIRDPARTYCALLED;
		}
	},
	THIRDPARTYCALLED(false) {
		@Override
		public Set<TransactionState> nextStates() {
			return EnumSet.of(BALANCEMANAGED, COMPLETED);
		}

		@Override
		public TransactionState nextState() {
			return BALANCEMANAGED;
		}
	},
	BALANCEMANAGED(false) {
		@Override
		public Set<TransactionState> nextStates() {
			return EnumSet.of(COMPLETED);
		}

		@Override
		public TransactionState nextState() {
			return COMPLETED;
		}
	},
	COMPLETED(false) {
		@Override
		public Set<TransactionState> nextStates() {
			return EnumSet.of(COMPLETED);
		}

		@Override
		public TransactionState nextState() {
			return COMPLETED;
		}
	};
	private final boolean editable;

	private TransactionState(final boolean editable) {
		this.editable = editable;
	}

	public abstract Set<TransactionState> nextStates();

	public abstract TransactionState nextState();

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
	public TransactionState validateNextState(final TransactionState state) {
		if (nextStates().contains(state)) {
			return state;
		} else {
			throw new IllegalStateException("Cannot transition from state "
					+ this + " to " + state);
		}
	}

}
