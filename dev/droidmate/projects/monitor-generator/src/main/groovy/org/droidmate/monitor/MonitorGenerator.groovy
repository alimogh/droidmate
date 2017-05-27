// DroidMate, an automated execution generator for Android apps.
// Copyright (C) 2012-2016 Konrad Jamrozik
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
// email: jamrozik@st.cs.uni-saarland.de
// web: www.droidmate.org

package org.droidmate.monitor

import groovy.util.logging.Slf4j
import org.droidmate.apis.ApiMethodSignature

@Slf4j
class MonitorGenerator implements IMonitorGenerator
{

  private final IRedirectionsGenerator redirectionsGenerator
  private final MonitorSrcTemplate     monitorSrcTemplate

  MonitorGenerator(
    IRedirectionsGenerator redirectionsGenerator,
    MonitorSrcTemplate monitorSrcTemplate)
  {
    this.redirectionsGenerator = redirectionsGenerator
    this.monitorSrcTemplate = monitorSrcTemplate
  }

  @Override
  String generate(List<ApiMethodSignature> signatures)
  {
    def (String genCtorCalls, String genCtorTargets) = redirectionsGenerator.generateCtorCallsAndTargets(signatures)
    String genMethodTargets = redirectionsGenerator.generateMethodTargets(signatures)

    return monitorSrcTemplate.injectGeneratedCode(genCtorCalls, genCtorTargets, genMethodTargets)
  }
}


