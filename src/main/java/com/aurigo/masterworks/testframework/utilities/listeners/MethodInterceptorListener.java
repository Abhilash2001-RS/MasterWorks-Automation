package com.aurigo.masterworks.testframework.utilities.listeners;

import com.aurigo.masterworks.testframework.BaseFramework;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;

public class MethodInterceptorListener extends BaseFramework implements IMethodInterceptor {

    /**
     * Overrides the default implementation to enable AND operation in groups
     *
     * @param list         List of TestNG method instances
     * @param iTestContext TestNG test context
     * @return List of filtered TestNG method instances based on the incoming groups and groupOperator in maven goal
     */
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> list, ITestContext iTestContext) {

        if (filteredTestMethods.size() > 0) {
            List<IMethodInstance> updatedList = new ArrayList<>();
            for (var methodInstance : list) {
                for (var method : filteredTestMethods) {
                    if (methodInstance.getMethod().equals(method))
                        updatedList.add(methodInstance);
                }
            }
            return updatedList;
        }

        return list;
    }
}
