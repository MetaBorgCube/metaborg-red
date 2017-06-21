package Smalltalk.interpreter.natives;

import org.metaborg.Smalltalk.interpreter.generated.TypesGen;
import org.metaborg.Smalltalk.interpreter.generated.terms.BoolV_1_Term;
import org.metaborg.Smalltalk.interpreter.generated.terms.IVTerm;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.source.SourceSection;

public class bool_call_native_3 extends TermBuild {

	@Child
	protected TermBuild opNode;
	@Child
	protected TermBuild leftNode;
	@Child
	protected TermBuild rightNode;

	public bool_call_native_3(SourceSection source, TermBuild op, TermBuild l, TermBuild r) {
		super(source);
		this.opNode = op;
		this.leftNode = l;
		this.rightNode = r;
	}

	@Override
	public IVTerm executeGeneric(VirtualFrame frame) {
		final String op = TypesGen.asString(opNode.executeGeneric(frame));
		final BoolV_1_Term left = TypesGen.asBoolV_1_Term(leftNode.executeGeneric(frame));
		final BoolV_1_Term right = TypesGen.asBoolV_1_Term(rightNode.executeGeneric(frame));
		switch (op) {
		case "|":
			return doOr(left, right);
		case "&":
			return doAnd(left, right);
		case "==":
			return doEq(left, right);
		case "~=":
			return doNeq(left, right);
		default:
			throw new IllegalArgumentException("operator: '" + op + "' not recognised as operator on boolean.");
		}
	}

	private IVTerm doOr(final BoolV_1_Term left, final BoolV_1_Term right) {
		return new BoolV_1_Term(left.get_1() || right.get_1());
	}

	private IVTerm doAnd(final BoolV_1_Term left, final BoolV_1_Term right) {
		return new BoolV_1_Term(left.get_1() && right.get_1());
	}

	private IVTerm doEq(final BoolV_1_Term left, final BoolV_1_Term right) {
		return new BoolV_1_Term(left.get_1() == right.get_1());
	}

	private IVTerm doNeq(final BoolV_1_Term left, final BoolV_1_Term right) {
		return new BoolV_1_Term(left.get_1() != right.get_1());
	}
	
	public static TermBuild create(SourceSection source, TermBuild op, TermBuild left, TermBuild right) {
		return new bool_call_native_3(source, op, left, right);
	}

}