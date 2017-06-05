package Smalltalk.interpreter.natives;

import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;
import org.metaborg.meta.nabl2.solver.TypeException;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@NodeChildren({ @NodeChild(value = "left", type = TermBuild.class),
    @NodeChild(value = "right", type = TermBuild.class),
    @NodeChild(value = "op", type = TermBuild.class) })
public abstract class binOpIB_3 extends TermBuild {

      public binOpIB_3(SourceSection source) {
              super(source);
      }

      @Specialization
      public boolean doInt(int left, int right, String op) {
    	  switch(op) {
    	  case "<=":
    		  return left <= right;
    	  case "<":
    		  return left < right;
    	  case ">=":
    		  return left >= right;
    	  case ">":
    		  return left > right;
    	  default:
    		  throw new TypeException("Operator " + op + " does not return a boolean");
    	  }
      }

      public static TermBuild create(SourceSection source, TermBuild left, TermBuild right, TermBuild op) {
              return binOpIB_3NodeGen.create(source, left, right, op);
      }
}