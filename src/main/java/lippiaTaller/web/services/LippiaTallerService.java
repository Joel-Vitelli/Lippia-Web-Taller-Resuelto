package lippiaTaller.web.services;

import com.crowdar.core.PropertyManager;
import com.crowdar.core.actions.ActionManager;
import com.crowdar.driver.DriverManager;
import lippiaTaller.web.constants.LippiaTallerConstants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

import static com.crowdar.core.actions.WebActionManager.*;

public class LippiaTallerService extends ActionManager {

    private static final ThreadLocal<String> descData = new ThreadLocal<>();

    public static void navegarWeb(){
        navigateTo(PropertyManager.getProperty("web.base.url"));
    }

    public static void navegarMenu(String subMenu1, String subMenu2){
        hoverItem(LippiaTallerConstants.SUBMENU_1_XPATH,subMenu1);
        click(LippiaTallerConstants.SUBMENU_2_XPATH,subMenu2);
    }

    public static void hoverItem(String locator, String locatorReemp){
        waitVisibility(locator,locatorReemp);
        WebElement ele = getElement(locator, locatorReemp);
        hoverItem(ele);
    }

    public static void hoverItem(WebElement ele){
        Actions action = new Actions(DriverManager.getDriverInstance());
        action.moveToElement(ele).perform();
    }

    public static void verificarNavegacion(String navegacion){
        String[] listaNavegacion = navegacion.split("Home");
        Assert.assertEquals(getText(LippiaTallerConstants.NAVEGACION_XPATH), listaNavegacion[1], "La navegación no es correcta");
    }

    public static void verificarEncabezado(String encabezado){
        waitVisibility(LippiaTallerConstants.ENCABEZADO_XPATH);
        Assert.assertEquals(getText(LippiaTallerConstants.ENCABEZADO_XPATH), encabezado, "El encabezado es incorrecto");
    }

    public static void verificarTituloListaFiltros(String tituloListaFiltros){
        waitVisibility(LippiaTallerConstants.TITULO_FILTRO_XPATH);
        Assert.assertEquals(getText(LippiaTallerConstants.TITULO_FILTRO_XPATH), tituloListaFiltros, "El titulo de los filtros no es correcto");
    }

    public static void verificarItems(){
        waitVisibilities(LippiaTallerConstants.LISTA_ITEMS_XPATH);
        List<WebElement> listaItems = getElements(LippiaTallerConstants.LISTA_ITEMS_XPATH);
        Assert.assertNotNull(listaItems, "La lista de items esta vacía");
    }

    public static void addItemToCar(){ //usamos un ThreadLocal para guardar la información de la descripción del Item y usarla en el proximo step
        waitVisibilities(LippiaTallerConstants.LISTA_ITEMS_XPATH);
        List<WebElement> listaItems = getElements(LippiaTallerConstants.LISTA_ITEMS_XPATH);
        hoverItem(listaItems.get(0));
        descData.set(listaItems.get(0).getAttribute("innerText").split("\n")[2]);
        click(LippiaTallerConstants.ADD_TO_CAR_XPATH);
    }

    public static void clickModalCompra(){
        click(LippiaTallerConstants.PROCED_TO_CHECKOUT_XPATH);
    }

    public static void verificarItemEnCarrito(){
        hoverItem(LippiaTallerConstants.CARRITO_COMPRAS_XPATH,"");
        waitVisibility(LippiaTallerConstants.ITEM_IN_CART_XPATH);
        Assert.assertEquals(descData.get(), getAttribute(LippiaTallerConstants.ITEM_IN_CART_XPATH, "title"));
    }

}
