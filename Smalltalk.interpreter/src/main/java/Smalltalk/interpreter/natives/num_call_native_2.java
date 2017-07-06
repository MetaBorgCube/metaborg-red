package Smalltalk.interpreter.natives;
import org.metaborg.Smalltalk.interpreter.generated.TypesGen;
import org.metaborg.Smalltalk.interpreter.generated.terms.BoolV_1_Term;
import org.metaborg.Smalltalk.interpreter.generated.terms.IVTerm;
import org.metaborg.Smalltalk.interpreter.generated.terms.NumV_1_Term;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.source.SourceSection;

public class num_call_native_2 extends TermBuild {

	@Child
	protected TermBuild opNode;
	@Child
	protected TermBuild termNode;

	public num_call_native_2(SourceSection source, TermBuild op, TermBuild termNode) {
		super(source);
		this.opNode = op;
		this.termNode = termNode;
	}

	@Override
	public IVTerm executeGeneric(VirtualFrame frame) {
		final String op = TypesGen.asString(opNode.executeGeneric(frame));
		final NumV_1_Term left = TypesGen.asNumV_1_Term(termNode.executeGeneric(frame));
		switch (op) {
		case "sqrt":
			return doSqrt(left);
		case "sign":
			return doSign(left);
		case "negated":
			return doNegated(left);
		case "squared":
			return doSquared(left);
		case "exp":
			return doExp(left);
		case "abs":
			return doAbs(left);
		case "factorial":
			return doFactorial(left);
		case "ln":
			return doLn(left);
		case "log":
			return doLog(left);
		case "atRandom":
			return doRandom(left);
		case "negative":
			return isNegative(left);
		case "positive":
			return isPositive(left);
		case "strictlyPositive":
			return isSPositive(left);
		case "even":
			return isEven(left);
		case "odd":
			return isOdd(left);
		case "isZero":
			return isZero(left);
		//everything else don't work with integers only (trigonimic functions, reciprocals)
		default:
			throw new IllegalArgumentException("operator: '" + op + "' not recognised as operator on number.");
		}
	}

	private IVTerm isZero(NumV_1_Term left) {
		return new BoolV_1_Term(left.get_1() == 0);
	}

	private IVTerm isOdd(NumV_1_Term left) {
		return new BoolV_1_Term(left.get_1() % 2 == 1);
	}

	private IVTerm isEven(NumV_1_Term left) {
		return new BoolV_1_Term(left.get_1() % 2 == 0);
	}

	private IVTerm isSPositive(NumV_1_Term left) {
		return new BoolV_1_Term(left.get_1() > 0);
	}

	private IVTerm isPositive(NumV_1_Term left) {
		return new BoolV_1_Term(left.get_1() >= 0);
	}

	private IVTerm isNegative(NumV_1_Term left) {
		return new BoolV_1_Term(left.get_1() < 0);
	}

	private IVTerm doRandom(NumV_1_Term left) {
		return new NumV_1_Term((int)(Math.random()*left.get_1() + 1));
	}

	private IVTerm doLog(NumV_1_Term left) {
		return new NumV_1_Term((int)Math.log10(left.get_1()));
	}

	private IVTerm doLn(NumV_1_Term left) {
		return new NumV_1_Term((int)Math.log(left.get_1()));
	}

	private IVTerm doFactorial(NumV_1_Term left) {
		return new NumV_1_Term(fact(left.get_1()));
	}

	private int fact(int n) {
		if(n<0) {
			throw new IllegalStateException("Factorial of a negative integer is not defined!");
		}
		if(n==0) {
			return 1;
		}
		return n*fact(n-1);
	}

	private IVTerm doAbs(NumV_1_Term left) {
		return new NumV_1_Term((int)Math.abs(left.get_1()));
	}

	private IVTerm doExp(NumV_1_Term left) {
		return new NumV_1_Term((int)Math.exp(left.get_1()));
	}

	private IVTerm doSquared(NumV_1_Term left) {
		return new NumV_1_Term(left.get_1()*left.get_1());
	}

	private IVTerm doNegated(NumV_1_Term left) {
		return new NumV_1_Term(-left.get_1());
	}

	private IVTerm doSign(NumV_1_Term left) {
		return new NumV_1_Term((int)Math.signum(left.get_1()));
	}

	private IVTerm doSqrt(NumV_1_Term left) {
		return new NumV_1_Term((int)Math.sqrt(left.get_1()));
	}

	public static TermBuild create(SourceSection source, TermBuild op, TermBuild term) {
		return new num_call_native_2(source, op, term);
	}

}