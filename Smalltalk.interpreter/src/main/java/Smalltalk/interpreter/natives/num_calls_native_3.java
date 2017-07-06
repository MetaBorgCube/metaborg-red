package Smalltalk.interpreter.natives;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.ArrayUtils;
import org.metaborg.Smalltalk.interpreter.generated.TypesGen;
import org.metaborg.Smalltalk.interpreter.generated.terms.BoolV_1_Term;
import org.metaborg.Smalltalk.interpreter.generated.terms.IKeywordMessageElementTerm;
import org.metaborg.Smalltalk.interpreter.generated.terms.IVTerm;
import org.metaborg.Smalltalk.interpreter.generated.terms.List_IKeywordMessageSegmentTerm;
import org.metaborg.Smalltalk.interpreter.generated.terms.List_IVTerm;
import org.metaborg.Smalltalk.interpreter.generated.terms.List_String;
import org.metaborg.Smalltalk.interpreter.generated.terms.NumV_1_Term;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.source.SourceSection;

public class num_calls_native_3 extends TermBuild {

	@Child
	protected TermBuild opNode;
	@Child
	protected TermBuild keysNode;
	@Child
	protected TermBuild valuesNode;

	public num_calls_native_3(SourceSection source, TermBuild keys, TermBuild values, TermBuild op) {
		super(source);
		this.opNode = op;
		this.keysNode = keys;
		this.valuesNode = values;
	}

	@Override
	public IVTerm executeGeneric(VirtualFrame frame) {
		
		final NumV_1_Term operand = TypesGen.asNumV_1_Term(opNode.executeGeneric(frame));
		final String[] keys = TypesGen.asList_String(keysNode.executeGeneric(frame)).toArray();
		final IVTerm[] values = TypesGen.asList_IVTerm(valuesNode.executeGeneric(frame)).toArray();
		
		return doOperations(operand, keys, values);
	}
	
	private IVTerm doOperations(NumV_1_Term operand, String[] keys, IVTerm[] values) {
		int n = keys.length;
		if(values.length != n) {
			throw new ArrayIndexOutOfBoundsException("amount of keys not equal to amount of values");
		}
		ArrayUtils.reverse(keys);
		ArrayUtils.reverse(values);
		//Check for known operations
		switch (n) {
		case 0:
			return operand;
		case 1:
			NumV_1_Term right;
			try {
				right = (NumV_1_Term) values[0];
			} catch(ClassCastException e) {
				throw new IllegalArgumentException("Operators on integers require integers as argument");
			}
			switch(keys[0]) { //again, only integer operations
			case "raisedTo":
			case  "raisedToInteger":
				return doPower(operand, right);
			case "log":
				return doLog(operand, right);
			case "floorLog":
				return doFloorLog(operand, right);
			case "min":
				return doMin(operand, right);
			case "max":
				return doMax(operand, right);
			}
		case 2:
			if(keys[0].equals("between") && keys[1].equals("and")) {
				return isBetween(operand, values[0], values[1]);
			}
		}
		throw new IllegalArgumentException("Unknown selectors on number.");
	}

	private IVTerm isBetween(NumV_1_Term operand, IVTerm ivTerm1, IVTerm ivTerm2) {
		NumV_1_Term first = (NumV_1_Term) ivTerm1;
		NumV_1_Term second = (NumV_1_Term) ivTerm2;
		int i = operand.get_1();
		return new BoolV_1_Term(i>first.get_1() && i<second.get_1());
	}

	private IVTerm doMax(NumV_1_Term operand, NumV_1_Term right) {
		return new NumV_1_Term(Math.max(operand.get_1(), right.get_1()));
	}
	
	private IVTerm doMin(NumV_1_Term operand, NumV_1_Term right) {
		return new NumV_1_Term(Math.min(operand.get_1(), right.get_1()));
	}

	private IVTerm doFloorLog(NumV_1_Term operand, NumV_1_Term right) {
		return new NumV_1_Term((int)Math.floor((double)Math.log10(operand.get_1())/(double)Math.log10(right.get_1())));
	}

	private IVTerm doLog(NumV_1_Term operand, NumV_1_Term right) {
		return new NumV_1_Term((int) (Math.log10(operand.get_1())/Math.log10(right.get_1())));
	}

	private IVTerm doPower(NumV_1_Term operand, NumV_1_Term right) {
		return new NumV_1_Term((int)Math.pow(operand.get_1(), right.get_1()));
		
	}

	public static TermBuild create(SourceSection source, TermBuild keys, TermBuild values, TermBuild op) {
		return new num_calls_native_3(source, keys, values, op);
	}

}