package requirements;

import net.thucydides.core.annotations.Feature;

/**
 * Created by Dauren_Altynbekov on 8/26/2015.
 */
public class GmailApplication {
    @Feature
    public class SendingMails {
        public class SendingMail {
        }

        public class SendingMailFromDraft {
        }

        public class SendingMailWithNoAddress {
        }
    }

    @Feature
    public class UserHelpManage {
        public class ShowHelpToolTip {
        }
    }
}
