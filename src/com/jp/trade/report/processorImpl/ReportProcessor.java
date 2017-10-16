package com.jp.trade.report.processorImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.jp.trade.report.model.Instruction;
import com.jp.trade.report.model.Rank;
import com.jp.trade.report.processor.Processor;
import com.jp.trade.report.settlementdate.calculator.InstructionSettlementDateCalculator;
import com.jp.trade.report.statistic.calculator.InstructionSettlementStatsCalculator;

/**
 *  {@link ReportProcessor} Implementation of Processor
 * @author premsharma
 *
 */
public class ReportProcessor implements Processor {

	private StringBuilder stringBuilder = new StringBuilder();

	/* (non-Javadoc)
	 * @see com.jp.trade.report.processor.Processor#generateInstructionsReport(java.util.Set)
	 */
	@Override
	public String generateInstructionsReport(Set<Instruction> instructions) {
		// first calculate the correct settlement dates
		InstructionSettlementDateCalculator.calculateSettlementDates(instructions);

		// Build the report string
		return generateDailyOutgoingRanking(instructions, generateDailyIncomingRanking(instructions,
				generateDailyIncomingAmount(instructions, generateDailyOutgoingAmount(instructions, stringBuilder))))
						.toString();
	}

	private static StringBuilder generateDailyOutgoingAmount(Set<Instruction> instructions,
			StringBuilder stringBuilder) {
		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = InstructionSettlementStatsCalculator
				.calculateDailyOutgoingAmount(instructions);

		stringBuilder.append("\n----------------------------------------\n")
				.append("         Outgoing Daily Amount          \n")
				.append("----------------------------------------\n")
				.append("      Date       |    Trade Amount      \n")
				.append("----------------------------------------\n");

		for (LocalDate date : dailyOutgoingAmount.keySet()) {
			stringBuilder.append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
		}

		return stringBuilder;
	}

	private static StringBuilder generateDailyIncomingAmount(Set<Instruction> instructions,
			StringBuilder stringBuilder) {
		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = InstructionSettlementStatsCalculator
				.calculateDailyIncomingAmount(instructions);

		stringBuilder.append("\n----------------------------------------\n")
				.append("         Incoming Daily Amount          \n")
				.append("----------------------------------------\n")
				.append("      Date       |    Trade Amount      \n")
				.append("----------------------------------------\n");

		for (LocalDate date : dailyOutgoingAmount.keySet()) {
			stringBuilder.append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
		}

		return stringBuilder;
	}

	private static StringBuilder generateDailyIncomingRanking(Set<Instruction> instructions,
			StringBuilder stringBuilder) {
		final Map<LocalDate, LinkedList<Rank>> dailyIncomingRanking = InstructionSettlementStatsCalculator
				.calculateDailyIncomingRanking(instructions);

		stringBuilder.append("\n----------------------------------------\n")
				.append("         Incoming Daily Ranking          \n")
				.append("----------------------------------------\n")
				.append("     Date    |     Rank   |   Entity     \n")
				.append("----------------------------------------\n");

		for (LocalDate date : dailyIncomingRanking.keySet()) {
			for (Rank rank : dailyIncomingRanking.get(date)) {
				stringBuilder.append(date).append("   |      ").append(rank.getRank()).append("     |    ")
						.append(rank.getEntity()).append("\n");
			}
		}

		return stringBuilder;
	}

	private static StringBuilder generateDailyOutgoingRanking(Set<Instruction> instructions,
			StringBuilder stringBuilder) {
		final Map<LocalDate, LinkedList<Rank>> dailyOutgoingRanking = InstructionSettlementStatsCalculator
				.calculateDailyOutgoingRanking(instructions);

		stringBuilder.append("\n----------------------------------------\n")
				.append("         Outgoing Daily Ranking          \n")
				.append("----------------------------------------\n")
				.append("     Date    |     Rank   |   Entity     \n")
				.append("----------------------------------------\n");

		for (LocalDate date : dailyOutgoingRanking.keySet()) {
			for (Rank rank : dailyOutgoingRanking.get(date)) {
				stringBuilder.append(date).append("   |      ").append(rank.getRank()).append("     |    ")
						.append(rank.getEntity()).append("\n");
			}
		}

		return stringBuilder;
	}
}
