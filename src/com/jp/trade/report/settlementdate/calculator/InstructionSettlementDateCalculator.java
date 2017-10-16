package com.jp.trade.report.settlementdate.calculator;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

import com.jp.trade.report.model.Instruction;
import com.jp.trade.report.trading.week.IWorkingDays;
import com.jp.trade.report.trading.weekImpl.ArabiaWorkingDays;
import com.jp.trade.report.trading.weekImpl.DefaultWorkingDays;

/**
 * A settlement date calculator which calculates the SettlementDate
 */
/**
 * @author prem sharma
 *
 */
public class InstructionSettlementDateCalculator {

    /**
     * Helper function to calculate settlement date for every given instruction
     * @param instructions the instructions of which the settlement date will be calculated
     */
    public static void calculateSettlementDates(Set<Instruction> instructions) {
        instructions.forEach(InstructionSettlementDateCalculator::calculateSettlementDate);
    }

    /**
     * Calculate the settlementDate Based on some logic
     * @param instruction the instruction of which the settlement date will be calculated
     */
    public static void calculateSettlementDate(Instruction instruction) {
        // Select proper strategy based on the Currency
        final IWorkingDays workingDaysMechanism = getWorkingDaysStrategy(instruction.getCurrency());

        // find the correct settlement date
        final LocalDate newSettlementDate =
                workingDaysMechanism.findFirstWorkingDate(instruction.getSettlementDate());

        if (newSettlementDate != null) {
            // set the correct settlement date
            instruction.setSettlementDate(newSettlementDate);
        }
    }

    /**
     * Select proper strategy based on the Currency
     * @param currency the currency to choose
     * @return the proper working days strategy
     */
    private static IWorkingDays getWorkingDaysStrategy(Currency currency) {
        if ((currency.getCurrencyCode().equals("AED")) ||
            (currency.getCurrencyCode().equals("SAR")))
        {
            return ArabiaWorkingDays.getInstance();
        }
        return DefaultWorkingDays.getInstance();
    }

}
