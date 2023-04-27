import javax.naming.AuthenticationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SuccessADAL1_1 {

    public static void main(String[] args) throws AuthenticationException, IOException {
        System.out.println("Start...");

        // Load context
        FileInputStream fis = new FileInputStream(args[0]);
        Properties context = new Properties();
        context.load(fis);

        String serviceRootURL = context.getProperty("service_root");
        String authEndpoint = context.getProperty("auth_endpoint");
        String clientId = context.getProperty("client_id");
        String clientSecret = context.getProperty("client_secret");
        String password = context.getProperty("pwd");
        String login = context.getProperty("login");

        int reConnMaxNum_tMicrosoftCrmInput_2 = 5;
        String entity = "po_lostandstolens";

        org.talend.ms.crm.odata.ClientConfiguration clientConfig = org.talend.ms.crm.odata.ClientConfigurationFactory
                .buildOAuthWebClientConfiguration(
                        clientId,
                        clientSecret,
                        login,
                        password,
                        authEndpoint,
                        org.talend.ms.crm.odata.ClientConfiguration.WebAppPermission.DELEGATED);

        clientConfig.setTimeout(60);
        clientConfig.setMaxRetry(reConnMaxNum_tMicrosoftCrmInput_2, 1000);
        clientConfig.setReuseHttpClient(false);

        org.talend.ms.crm.odata.DynamicsCRMClient client = new org.talend.ms.crm.odata.DynamicsCRMClient(
                clientConfig,
                serviceRootURL,
                entity);

        System.out.println("===> " + client);
    }

}
