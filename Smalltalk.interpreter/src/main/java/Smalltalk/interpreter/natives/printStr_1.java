package Smalltalk.interpreter.natives;

import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@NodeChild(value = "stringbuild", type = TermBuild.class)
public abstract class printStr_1 extends TermBuild {

      public printStr_1(SourceSection source) {
              super(source);
      }

      @Specialization
      public int doInt(String s) {
    	  	  System.out.println(s);
              return 0;
      }

      public static TermBuild create(SourceSection source, TermBuild stringbuild) {
              return printStr_1NodeGen.create(source, stringbuild);
      }
}