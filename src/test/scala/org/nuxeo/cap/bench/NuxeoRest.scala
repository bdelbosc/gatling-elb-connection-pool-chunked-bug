package org.nuxeo.cap.bench

/*
 * (C) Copyright 2015 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Delbosc Benoit
 */

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

object NuxeoRest {

  def encodePath = (path: String) => {
    java.net.URLEncoder.encode(path, "UTF-8")
  }

  def createUserIfNotExists = (groupName: String) => {
    exitBlockOnFail {
      exec(
        http("Check if user exists")
          .head("/api/v1/user/${user}")
          .headers(Headers.base)
          .header("Content-Type", "application/json")
          .basicAuth("${adminId}", "${adminPassword}")
          .check(status.in(404)))
        .exec(
          http("Create user")
            .post("/api/v1/user")
            .headers(Headers.default)
            .header("Content-Type", "application/json")
            .basicAuth("${adminId}", "${adminPassword}")
            .body(StringBody(
              """{"entity-type":"user","id":"${user}","properties":{"firstName":null,"lastName":null,"password":"${password}","groups":["""" +
                groupName +
                """"],"company":null,"email":"devnull@nuxeo.com","username":"${user}"},"extendedGroups":[{"name":"members","label":"Members group","url":"group/members"}],"isAdministrator":false,"isAnonymous":false}"""))
            .check(status.in(201)))
    }
  }

  def deleteUser = () => {
          http("Delete user")
          .delete("/api/v1/user/${user}")
          .headers(Headers.base)
          .header("Content-Type", "application/json")
          .basicAuth("${adminId}", "${adminPassword}")
          .check(status.in(204,404))
 }

  def createGroup = (groupName: String) => {
          http("Create group")
            .post("/api/v1/group")
            .headers(Headers.default)
            .header("Content-Type", "application/json")
            .basicAuth("${adminId}", "${adminPassword}")
            .body(StringBody(
              """{"entity-type":"group","groupname":"""" + groupName + """", "groupLabel": "Gatling group"}"""))
            .check(status.in(201,500))
  }

  def deleteGroup = (groupName: String) => {
    http("Delete user")
      .delete("/api/v1/group/" + groupName)
      .headers(Headers.base)
      .header("Content-Type", "application/json")
      .basicAuth("${adminId}", "${adminPassword}")
      .check(status.in(204))
  }

}
