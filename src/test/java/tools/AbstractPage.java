package tools;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Make jason update this comment block.
 *
 * @author jason
 * @since 5/24/16
 */
public class AbstractPage extends AbstractBase
{
    public AbstractPage(RemoteWebDriver d, WebDriverWait w)
    {
        driver = d;
        wait = w;
    }
}
