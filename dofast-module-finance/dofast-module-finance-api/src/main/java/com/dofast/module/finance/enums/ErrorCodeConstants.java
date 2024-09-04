package com.dofast.module.finance.enums;

import com.dofast.framework.common.exception.ErrorCode;


public interface ErrorCodeConstants {
	// === 业务发票关联 1019000000===
	ErrorCode CASH_TRADE_INVOICE_NOT_EXISTS = new ErrorCode(1019000001, "业务发票关联不存在");

	// === 现金余额 1019001000 ===
	ErrorCode CASH_BALANCE_NOT_EXISTS = new ErrorCode(1019001001, "现金余额不存在");

	// === 资金账号 1019002000 ===
	ErrorCode CASH_DEPOSITOR_NOT_EXISTS = new ErrorCode(1019002001, "资金账号不存在");

	// === 财务退款 1019003000 ===
	ErrorCode CASH_FUND_NOT_EXISTS = new ErrorCode(1019003001, "财务退款不存在");

	// === 发票信息 1019004000 ===
	ErrorCode CASH_INVOICE_NOT_EXISTS = new ErrorCode(1019004001, "发票信息不存在");

	// === 财务发票明细 1019005000 ===
	ErrorCode CASH_INVOICE_DETAIL_NOT_EXISTS = new ErrorCode(1019005001, "财务发票明细不存在");

	// === 财务流水 1019006000 ===
	ErrorCode CASH_TRADE_NOT_EXISTS = new ErrorCode(1019006001, "财务流水不存在");

	// === 收支科目 TODO 收支科目不存在 ===
	ErrorCode SUBJECT_RELATED_NOT_EXISTS = new ErrorCode(1019006002, "收支科目不存在");

}