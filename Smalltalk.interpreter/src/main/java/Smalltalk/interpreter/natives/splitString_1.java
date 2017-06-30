package Smalltalk.interpreter.natives;

import org.metaborg.Smalltalk.interpreter.generated.TypesGen;
import org.metaborg.Smalltalk.interpreter.generated.terms.IVTerm;
import org.metaborg.Smalltalk.interpreter.generated.terms.List_String;
import org.metaborg.Smalltalk.interpreter.generated.terms.NilV_0_Term;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.source.SourceSection;

public class splitString_1 extends TermBuild {

	@Child
	protected TermBuild value;

	public splitString_1(SourceSection source, TermBuild value) {
		super(source);
		this.value = value;
	}

	public static TermBuild create(SourceSection source, TermBuild termBuild) {
		return new splitString_1(source, termBuild);
	}

	@Override
	public List_String executeGeneric(VirtualFrame frame) {
		String str = TypesGen.asString(value.executeGeneric(frame));
		String[] splits = str.split(" ");
		return new List_String(splits);
	}
}
