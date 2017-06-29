package Smalltalk.interpreter.natives;

import org.metaborg.Smalltalk.interpreter.generated.TypesGen;
import org.metaborg.Smalltalk.interpreter.generated.terms.IVTerm;
import org.metaborg.Smalltalk.interpreter.generated.terms.NilV_0_Term;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.source.SourceSection;

public class printV_1 extends TermBuild {

	@Child
	protected TermBuild value;

	public printV_1(SourceSection source, TermBuild value) {
		super(source);
		this.value = value;
	}

	public static TermBuild create(SourceSection source, TermBuild termBuild) {
		return new printV_1(source, termBuild);
	}

	@Override
	public IVTerm executeGeneric(VirtualFrame frame) {
		Object term = value.executeGeneric(frame);
		print(term);
		return new NilV_0_Term();
	}

	public void print(Object term) {
		String className = term.getClass().getSimpleName();
		switch(className) {
		case "BoolV_1_Term":
			System.out.println(TypesGen.asBoolV_1_Term(term).get_1());
			break;
		case "NumV_1_Term":
			System.out.println(TypesGen.asNumV_1_Term(term).get_1());
			break;
		case "StringV_1_Term":
			System.out.println(TypesGen.asStringV_1_Term(term).get_1());
			break;
		case "MethodExpressionV_2_Term":
			System.out.println("Method Expression V: " );
		default:
			System.out.println(className);
		}
	}
}
