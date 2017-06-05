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
public abstract class binOpI_3 extends TermBuild {

      public binOpI_3(SourceSection source) {
              super(source);
      }

      @Specialization
      public int doInt(int left, int right, String op) {
    	  switch(op) {
    	  case "+":
    		  return left + right;
    	  case "-":
    		  return left - right;
    	  case "*":
    		  return left * right;
    	  case "/":
    		  return left/right;
    	  case "%":
    		  return left % right;
    	  default:
    		  throw new TypeException("Operator " + op + " does not return an integer");
    	  }
      }

      public static TermBuild create(SourceSection source, TermBuild left, TermBuild right, TermBuild op) {
              return binOpI_3NodeGen.create(source, left, right, op);
      }
}