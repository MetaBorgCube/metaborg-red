package Smalltalk.interpreter.natives;

import java.util.SortedMap;
import java.util.TreeMap;

import org.metaborg.Smalltalk.interpreter.generated.TypesGen;
import org.metaborg.Smalltalk.interpreter.generated.terms.BoolV_1_Term;
import org.metaborg.Smalltalk.interpreter.generated.terms.IVTerm;
import org.metaborg.Smalltalk.interpreter.generated.terms.NilV_0_Term;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node.Child;
import com.oracle.truffle.api.source.SourceSection;

public class bool_calls_native_3 extends TermBuild {

	@Child
	protected TermBuild opNode;
	@Child
	protected TermBuild keysNode;
	@Child
	protected TermBuild valuesNode;

	public bool_calls_native_3(SourceSection source, TermBuild keys, TermBuild values, TermBuild op) {
		super(source);
		this.opNode = op;
		this.keysNode = keys;
		this.valuesNode = values;
	}
	
	public IVTerm executeGeneric(VirtualFrame frame) {
		final BoolV_1_Term operand = TypesGen.asBoolV_1_Term(opNode.executeGeneric(frame));
		final String[] keys = TypesGen.asList_String(keysNode.executeGeneric(frame)).toArray();
		final IVTerm[] values = TypesGen.asList_IVTerm(valuesNode.executeGeneric(frame)).toArray();
		
		return doOperations(operand, keys, values);
	}
	
	private IVTerm doOperations(BoolV_1_Term operand, String[] keys, IVTerm[] values) {
		int n = keys.length;
		if(values.length != n) {
			throw new ArrayIndexOutOfBoundsException("amount of keys not equal to amount of values");
		}
		//sort
		SortedMap<String, IVTerm> map = new TreeMap<String, IVTerm>();
		for(int i = 0; i<n; i++) {
			map.put(keys[i], values[i]);
		}
		
		//Check for known operations
		switch (n) {
		case 0:
			return operand;
		case 1:
			switch(map.firstKey()) { //again, only integer operations
			case "ifTrue": 
				if(operand.get_1()) {
					return values[0];
				} else {
					return new NilV_0_Term();
				}
			case "ifFalse":
				if(operand.get_1()) {
					return new NilV_0_Term();
				} else {
					return values[0];
				}
			}
		case 2:
			switch(map.firstKey() + map.lastKey()) {
			case "ifFalseifTrue":
				if(operand.get_1()) {
					return values[1];
				} else {
					return values[0];
				}
			}
		}
		throw new IllegalArgumentException("Unknown selectors on boolean.");
	}
	
	public static TermBuild create(SourceSection source, TermBuild keys, TermBuild values, TermBuild op) {
		return new bool_calls_native_3(source, keys, values, op);
	}

}
