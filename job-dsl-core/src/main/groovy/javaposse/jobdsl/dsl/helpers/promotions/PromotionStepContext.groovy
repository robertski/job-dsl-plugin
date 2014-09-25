package javaposse.jobdsl.dsl.helpers.promotions

import groovy.transform.InheritConstructors
import javaposse.jobdsl.dsl.helpers.AbstractContextHelper
import javaposse.jobdsl.dsl.helpers.common.DownstreamContext
import javaposse.jobdsl.dsl.helpers.step.StepContext
import javaposse.jobdsl.dsl.JobManagement

@InheritConstructors
class PromotionStepContext extends StepContext {

    PromotionStepContext(List<Node> stepNodes = [], JobManagement jobManagement) {
        super(stepNodes, jobManagement)
    }

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
