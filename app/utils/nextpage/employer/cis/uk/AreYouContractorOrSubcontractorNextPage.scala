/*
 * Copyright 2018 HM Revenue & Customs
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

package utils.nextpage.employer.cis.uk

import config.FrontendAppConfig
import identifiers.AreYouContractorOrSubcontractorId
import models.employer.cis.uk.AreYouContractorOrSubcontractor
import controllers.employer.cis.ukbased.contractor.routes._
import controllers.employer.cis.ukbased.subcontractor.routes._
import play.api.mvc.{Call, Request}
import uk.gov.hmrc.auth.core.Enrolments
import utils.{HmrcEnrolmentType, NextPage}

trait AreYouContractorOrSubcontractorNextPage {

  type AreYouContractorOrSubcontractorWithRequest = (AreYouContractorOrSubcontractor, Enrolments)

  implicit val areYouContractorOrSubcontractor
    : NextPage[AreYouContractorOrSubcontractorId.type, AreYouContractorOrSubcontractorWithRequest] = {

    new NextPage[AreYouContractorOrSubcontractorId.type, AreYouContractorOrSubcontractorWithRequest] {

      override def get(contractorOrSubcontractor: AreYouContractorOrSubcontractorWithRequest)(
        implicit appConfig: FrontendAppConfig,
        request: Request[_]): Call =
        (contractorOrSubcontractor._1, hasEPayeEnrolment(contractorOrSubcontractor._2)) match {

          case (AreYouContractorOrSubcontractor.Contractor, true) =>
            Call("GET", appConfig.getPortalUrl("cisUkContractorEnrol"))

          case (AreYouContractorOrSubcontractor.Contractor, false) => IsBusinessRegisteredForPAYEController.onPageLoad()

          case (AreYouContractorOrSubcontractor.Subcontractor, _) => WhatTypeOfSubcontractorController.onPageLoad()
        }
    }
  }

  private def hasEPayeEnrolment(enrolments: Enrolments) =
    utils.Enrolments
      .hasEnrolments(enrolments, HmrcEnrolmentType.EPAYE)
}