package com.jp.trade.report.processor;

import java.util.Set;

import com.jp.trade.report.model.Instruction;

/**
 * {@link Processor} process the given set of instructions with all business rules applied and generates Report
 * @author psha84
 *
 */
public interface Processor {
	
	    /** 
	     *  process the instructions to generate report and applies all business rules 
	     * @param instructions
	     * @return String: Formatted Report in string format 
	     */
	    String generateInstructionsReport(Set<Instruction> instructions);
	}


