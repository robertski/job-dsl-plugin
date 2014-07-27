package javaposse.jobdsl.dsl.helpers.promotions

//import com.google.common.base.Preconditions
import groovy.transform.InheritConstructors
//import javaposse.jobdsl.dsl.WithXmlAction
import javaposse.jobdsl.dsl.helpers.AbstractContextHelper
//import javaposse.jobdsl.dsl.helpers.Context
import javaposse.jobdsl.dsl.helpers.common.DownstreamContext
import javaposse.jobdsl.dsl.helpers.step.AbstractStepContext

@InheritConstructors
class PromotionStepContext extends AbstractStepContext {
   /**
     Trigger parameterized build on other projects using the publisher syntax
     intead of the default behavior of the AbstractStepContext.
   */
    def downstreamParameterized(Closure downstreamClosure) {
        DownstreamContext downstreamContext = new DownstreamContext()
        AbstractContextHelper.executeInContext(downstreamClosure, downstreamContext)

        def stepNode = downstreamContext.createDownstreamNode(false)
        this.stepNodes << stepNode
    }
}
