package com.coinbase

class TransactionLedger {
    List<BuyTransaction> buys = []
    List<SellTransaction> sells = []
    List<SendTransaction> sends = []
    List<RequestTransaction> requests = []
    List<TransferTransaction>transfers = []
    List<FiatDepositTransaction>fiatDeposits = []
    List<FiatWithdrawalTransaction>fiatWithdrawals = []
    List<ExchangeDepositTransaction>exchangeDeposits = []
    List<ExchangeWithdrawalTransaction>exchangeWithdrawals = []
    List<VaultWithdrawalTransaction>vaultWithdrawals = []
    List<Transaction>unknowns = []
}
