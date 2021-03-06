/*
w * Copyright 2017 StreamSets Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.trivadis.streamsets.stage.executor.spark.livy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ValueChooserModel;

public class LivyConfigBean {
  @ConfigDef(
      type = ConfigDef.Type.STRING,
      required = true,
      label = "Driver Memory",
      group = "SPARK",
      displayPosition = 30
  )
  public String driverMemory = "";

  @ConfigDef(
      type = ConfigDef.Type.STRING,
      required = true,
      label = "Executor Memory",
      group = "SPARK",
      displayPosition = 40
  )
  public String executorMemory = "";

  @ConfigDef(
      type = ConfigDef.Type.BOOLEAN,
      required = true,
      defaultValue = "true",
      label = "Dynamic Allocation",
      description = "Enable the dynamic allocation of Spark worker nodes",
      group = "SPARK",
      displayPosition = 50
  )
  public boolean dynamicAllocation = true;

  @ConfigDef(
      type = ConfigDef.Type.NUMBER,
      required = true,
      min = 0,
      label = "Minimum Number of Worker Nodes",
      dependsOn = "dynamicAllocation",
      triggeredByValue = "true",
      group = "SPARK",
      displayPosition = 60

  )
  public int minExecutors;

  @ConfigDef(
      type = ConfigDef.Type.NUMBER,
      required = true,
      min = 0,
      label = "Maximum Number of Worker Nodes",
      dependsOn = "dynamicAllocation",
      triggeredByValue = "true",
      group = "SPARK",
      displayPosition = 70
  )
  public int maxExecutors;

  @ConfigDef(
      type = ConfigDef.Type.NUMBER,
      required = true,
      min = 1,
      label = "Number of Worker Nodes",
      dependsOn = "dynamicAllocation",
      triggeredByValue = "false",
      group = "SPARK",
      displayPosition = 80
  )
  public int numExecutors;

  @ConfigDef(
      type = ConfigDef.Type.STRING,
      required = false,
      label = "Proxy User",
      group = "SPARK",
      displayPosition = 110
  )
  public String proxyUser = "";

  @ConfigDef(
      type = ConfigDef.Type.LIST,
      required = false,
      label = "(NOT) Additional Spark Arguments",
      description = "Use this to pass any additional arguments to Spark Launcher/Spark Submit. Overrides other parameters",
      group = "SPARK",
      displayPosition = 140
  )
  public List<String> noValueArgs = new ArrayList<>();

  @ConfigDef(
      type = ConfigDef.Type.MAP,
      required = false,
      label = "(NOT) Additional Spark Arguments and Values",
      description = "Use this to pass any additional arguments to Spark Launcher/Spark Submit. Overrides other parameters",
      group = "SPARK",
      displayPosition = 150
  )
  public Map<String, String> args = new HashMap<>();

  @ConfigDef(
      type = ConfigDef.Type.MAP,
      required = false,
      label = "(NOT) Environment Variables",
      group = "SPARK",
      displayPosition = 160
  )
  public Map<String, String> env = new HashMap<>();

  /*
   * APPLICATION group.
   */
  @ConfigDef(
      type = ConfigDef.Type.MODEL,
      required = true,
      label = "Language",
      group = "APPLICATION",
      displayPosition = 10
  )
  @ValueChooserModel(LanguageChooserValues.class)
  public Language language;

  @ConfigDef(
      type = ConfigDef.Type.STRING,
      required = true,
      label = "Application Name",
      group = "APPLICATION",
      displayPosition = 20
  )
  public String appName = "";

  @ConfigDef(
      type = ConfigDef.Type.STRING,
      required = true,
      label = "Application Resource",
      group = "APPLICATION",
      displayPosition = 30
  )
  public String appResource = "";

  @ConfigDef(
      type = ConfigDef.Type.STRING,
      required = true,
      label = "Main Class",
      group = "APPLICATION",
      dependsOn = "language",
      triggeredByValue = "JVM",
      displayPosition = 40
  )
  public String mainClass = "";

  @ConfigDef(
      type = ConfigDef.Type.LIST,
      required = false,
      label = "Application Arguments",
      group = "APPLICATION",
      displayPosition = 50
  )
  public List<String> appArgs = new ArrayList<>();

  @ConfigDef(
      type = ConfigDef.Type.LIST,
      required = false,
      label = "Additional JARs",
      group = "APPLICATION",
      dependsOn = "language",
      triggeredByValue = "JVM",
      displayPosition = 60
  )
  public List<String> additionalJars = new ArrayList<>();

  @ConfigDef(
      type = ConfigDef.Type.LIST,
      required = false,
      label = "Dependencies",
      description = "Full path to additional Python files required by the application resource",
      group = "APPLICATION",
      dependsOn = "language",
      triggeredByValue = "PYTHON",
      displayPosition = 70
  )
  public List<String> pyFiles = new ArrayList<>();

  @ConfigDef(
      type = ConfigDef.Type.LIST,
      required = false,
      label = "Additional Files",
      group = "APPLICATION",
      displayPosition = 80
  )
  public List<String> additionalFiles = new ArrayList<>();

  @ConfigDef(
      type = ConfigDef.Type.BOOLEAN,
      required = true,
      defaultValue = "false",
      label = "(NOT) Wait for Completion",
      group = "APPLICATION",
      displayPosition = 90
  )
  public boolean waitForCompletion;
  
  @ConfigDef(
      type = ConfigDef.Type.NUMBER,
      required = true,
      defaultValue = "0",
      min = 0,
      label = "Maximum Time to Wait (ms)",
      description = "Time to wait for the app to complete. 0 to wait forever.",
      dependsOn = "waitForCompletion",
      triggeredByValue = "true",
      displayPosition = 100
  )
  public long waitTimeout;

  @ConfigDef(
      type = ConfigDef.Type.BOOLEAN,
      required = true,
      defaultValue = "false",
      label = "Enable Verbose Logging",
      description = "Enable only for testing, as a lot of additional log data is written to sdc.log",
      group = "APPLICATION",
      displayPosition = 110
  )
  public boolean verbose;

}
