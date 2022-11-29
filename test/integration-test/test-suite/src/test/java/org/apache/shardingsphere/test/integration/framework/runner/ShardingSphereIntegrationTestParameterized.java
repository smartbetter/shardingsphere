/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.test.integration.framework.runner;

import org.apache.shardingsphere.test.integration.env.runtime.IntegrationTestEnvironment;
import org.apache.shardingsphere.test.integration.env.runtime.cluster.ClusterEnvironment;
import org.apache.shardingsphere.test.integration.framework.runner.parallel.ParameterizedParallelRunnerScheduler;
import org.apache.shardingsphere.test.runner.ParallelRunningStrategy;
import org.apache.shardingsphere.test.runner.executor.ParallelRunnerExecutors;
import org.junit.runners.Parameterized;

/**
 * ShardingSphere integration test parameterized.
 */
public final class ShardingSphereIntegrationTestParameterized extends Parameterized {
    
    // CHECKSTYLE:OFF
    public ShardingSphereIntegrationTestParameterized(final Class<?> clazz) throws Throwable {
        // CHECKSTYLE:ON
        super(clazz);
        if (ClusterEnvironment.Type.DOCKER != IntegrationTestEnvironment.getInstance().getClusterEnvironment().getType()) {
            ParallelRunningStrategy parallelRunningStrategy = clazz.getAnnotation(ParallelRunningStrategy.class);
            if (null != parallelRunningStrategy) {
                setScheduler(new ParameterizedParallelRunnerScheduler(parallelRunningStrategy.value(), new ParallelRunnerExecutors()));
            }
        }
    }
}
