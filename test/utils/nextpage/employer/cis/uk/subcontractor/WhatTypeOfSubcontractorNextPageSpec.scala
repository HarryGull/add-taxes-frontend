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

package utils.nextpage.employer.cis.uk.subcontractor

import models.employer.cis.uk.subcontractor.WhatTypeOfSubcontractor
import utils.NextPage
import utils.nextpage.NextPageSpecBase

class WhatTypeOfSubcontractorNextPageSpec extends NextPageSpecBase {

  "whatTypeOfSubcontractor" when {
    behave like nextPage(
      NextPage.whatTypeOfSubcontractor,
      WhatTypeOfSubcontractor.SoleTrader,
      "/business-account/add-tax/employer/cis/uk/subcontractor/sole-trader"
    )

    behave like nextPage(
      NextPage.whatTypeOfSubcontractor,
      WhatTypeOfSubcontractor.Partnership,
      "https://www.gov.uk/government/publications/construction-industry-scheme-partnership-registration-cis304"
    )

    behave like nextPage(
      NextPage.whatTypeOfSubcontractor,
      WhatTypeOfSubcontractor.LimitedCompany,
      "https://www.gov.uk/government/publications/construction-industry-scheme-company-registration-cis305"
    )
  }
}
