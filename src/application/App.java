package application;

import java.util.List;
import java.util.Set;

import com.jp.trade.report.instruction.generator.InstructionsGenerator;
import com.jp.trade.report.model.Instruction;
import com.jp.trade.report.processorImpl.ReportProcessor;

public class App {

	public static void main(String[] args) {
		Set<Instruction> list=InstructionsGenerator.getFakeInstructions();
		ReportProcessor processor=new ReportProcessor();
		System.out.println(processor.generateInstructionsReport(list));
		

	}

}
