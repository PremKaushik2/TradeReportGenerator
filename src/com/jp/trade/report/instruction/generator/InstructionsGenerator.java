package com.jp.trade.report.instruction.generator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.jp.trade.report.model.Instruction;
import com.jp.trade.report.model.InstructionDetails;
import com.jp.trade.report.model.TradeAction;

public class InstructionsGenerator {
	public static Set<Instruction> getFakeInstructions() {
		List<Instruction> list = (Arrays.asList(

				new Instruction("E1", TradeAction.BUY, LocalDate.of(2017, 3, 10), LocalDate.of(2017, 3, 20),
						new InstructionDetails(Currency.getInstance("SGD"), BigDecimal.valueOf(0.50), 200,
								BigDecimal.valueOf(100.25))),

		new Instruction("E2", TradeAction.BUY, LocalDate.of(2017, 3, 10), LocalDate.of(2017, 3, 19),
				new InstructionDetails(Currency.getInstance("AED"), BigDecimal.valueOf(0.22), 450,
						BigDecimal.valueOf(150.5))),

		new Instruction("E3", TradeAction.BUY, LocalDate.of(2017, 3, 10), LocalDate.of(2017, 3, 18),
				new InstructionDetails(Currency.getInstance("SAR"), BigDecimal.valueOf(0.27), 150,
						BigDecimal.valueOf(400.8))),

		new Instruction("E4", TradeAction.SELL, LocalDate.of(2017, 3, 10), LocalDate.of(2017, 3, 21),
				new InstructionDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.34), 50,
						BigDecimal.valueOf(500.6))),

		new Instruction("E5", TradeAction.BUY, LocalDate.of(2017, 3, 10), LocalDate.of(2017, 3, 21),
				new InstructionDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.34), 20,
						BigDecimal.valueOf(40.6))),

		new Instruction("E6", TradeAction.BUY, LocalDate.of(2017, 3, 10), LocalDate.of(2017, 3, 21),
				new InstructionDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.34), 20,
						BigDecimal.valueOf(40.6))),

		new Instruction("E7", TradeAction.SELL, LocalDate.of(2017, 3, 10), LocalDate.of(2017, 3, 21),
				new InstructionDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.34), 1000,
						BigDecimal.valueOf(160.6))),

		new Instruction("E8", TradeAction.SELL, LocalDate.of(2017, 3, 10), LocalDate.of(2017, 3, 21),
				new InstructionDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.34), 120,
						BigDecimal.valueOf(500.6)))));

		;
		HashSet<Instruction> set=new HashSet<>(list);
		return set;
	}
}