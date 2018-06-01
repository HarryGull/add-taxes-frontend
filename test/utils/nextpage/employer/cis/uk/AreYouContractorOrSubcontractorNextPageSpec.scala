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

import models.employer.cis.uk.AreYouContractorOrSubcontractor
import utils.NextPage
import utils.nextpage.NextPageSpecBase

class AreYouContractorOrSubcontractorNextPageSpec extends NextPageSpecBase {

  "areYouContractorOrSubcontractor" when {

    behave like nextPageWithEnrolments(
      NextPage.areYouContractorOrSubcontractor,
      (AreYouContractorOrSubcontractor.Contractor, serviceRequest),
      AreYouContractorOrSubcontractor.Contractor.toString,
      "/business-account/add-tax/employer/cis/uk/contractor",
      "no Enrolments"
    )

    behave like nextPageWithEnrolments(
      NextPage.areYouContractorOrSubcontractor,
      (AreYouContractorOrSubcontractor.Contractor, createServiceRequest(Set(epayeEnrolment))),
      AreYouContractorOrSubcontractor.Contractor.toString,
      "http://localhost:8080/portal/service/construction-ind-scheme?action=enrol&step=enterdetails&lang=eng",
      "EPAYE Enrolments"
    )

    behave like nextPageWithEnrolments(
      NextPage.areYouContractorOrSubcontractor,
      (AreYouContractorOrSubcontractor.Subcontractor, serviceRequest),
      AreYouContractorOrSubcontractor.Subcontractor.toString,
      "/forms/form/register-a-partner-or-a-partnership-for-self-assessment/start#1",
      "no Enrolment"
    )
  }
}