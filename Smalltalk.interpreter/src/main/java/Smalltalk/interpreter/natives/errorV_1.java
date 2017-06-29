package Smalltalk.interpreter.natives;

import org.metaborg.Smalltalk.interpreter.generated.TypesGen;
import org.metaborg.Smalltalk.interpreter.generated.terms.IVTerm;
import org.metaborg.Smalltalk.interpreter.generated.terms.NilV_0_Term;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.source.SourceSection;

public class errorV_1 extends TermBuild {

	@Child
	protected TermBuild value;

	public errorV_1(SourceSection source, TermBuild value) {
		super(source);
		this.value = value;
	}

	public static TermBuild create(SourceSection source, TermBuild termBuild) {
		return new errorV_1(source, termBuild);
	}

	@Override
	public IVTerm executeGeneric(VirtualFrame frame) {
		String err = TypesGen.asString(value.executeGeneric(frame));
		System.out.println("Error: " + err);
		System.exit(1);
		return new NilV_0_Term();
	}
}
