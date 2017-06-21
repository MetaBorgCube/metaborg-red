package Smalltalk.interpreter.natives;

import org.metaborg.Smalltalk.interpreter.generated.TypesGen;
import org.metaborg.Smalltalk.interpreter.generated.terms.BoolV_1_Term;
import org.metaborg.Smalltalk.interpreter.generated.terms.IVTerm;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.source.SourceSection;

public class bool_call_native_2 extends TermBuild {

	@Child
	protected TermBuild opNode;
	@Child
	protected TermBuild termNode;

	public bool_call_native_2(SourceSection source, TermBuild op, TermBuild term) {
		super(source);
		this.opNode = op;
		this.termNode = term;
	}

	@Override
	public IVTerm executeGeneric(VirtualFrame frame) {
		final String op = TypesGen.asString(opNode.executeGeneric(frame));
		final BoolV_1_Term term = TypesGen.asBoolV_1_Term(termNode.executeGeneric(frame));
		switch (op) {
		case "not":
			return doNot(term);
		default:
			throw new IllegalArgumentException("operator: '" + op + "' not recognised as operator on bool.");
		}
	}

	private IVTerm doNot(final BoolV_1_Term left) {
		return new BoolV_1_Term(!left.get_1());
	}

	public static TermBuild create(SourceSection source, TermBuild op, TermBuild term) {
		return new bool_call_native_2(source, op, term);
	}

}