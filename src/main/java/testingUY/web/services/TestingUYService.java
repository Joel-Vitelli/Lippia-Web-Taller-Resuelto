package testingUY.web.services;

import com.crowdar.core.PropertyManager;
import com.crowdar.core.actions.ActionManager;
import com.crowdar.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import testingUY.web.constants.TestingUYConstants;

import java.util.List;

import static com.crowdar.core.actions.WebActionManager.*;

public class TestingUYService extends ActionManager {

    private static final ThreadLocal<String> descData = new ThreadLocal<>();

    public static void navegarWeb(){
        navigateTo(PropertyManager.getProperty("web.base.url"));
    }

    public static void navegarMenu(String subMenu1, String subMenu2){
        hoverItem(TestingUYConstants.SUBMENU_1_XPATH,subMenu1);
        click(TestingUYConstants.SUBMENU_2_XPATH,subMenu2);
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
        Assert.assertEquals(getText(TestingUYConstants.NAVEGACION_XPATH), listaNavegacion[1], "La navegación no es correcta");
    }

    public static void verificarEncabezado(String encabezado){
        waitVisibility(TestingUYConstants.ENCABEZADO_XPATH);
        Assert.assertEquals(getText(TestingUYConstants.ENCABEZADO_XPATH), encabezado, "El encabezado es incorrecto");
    }

    public static void verificarTituloListaFiltros(String tituloListaFiltros){
        waitVisibility(TestingUYConstants.TITULO_FILTRO_XPATH);
        Assert.assertEquals(getText(TestingUYConstants.TITULO_FILTRO_XPATH), tituloListaFiltros, "El titulo de los filtros no es correcto");
    }

    public static void verificarItems(){
        waitVisibilities(TestingUYConstants.LISTA_ITEMS_XPATH);
        List<WebElement> listaItems = getElements(TestingUYConstants.LISTA_ITEMS_XPATH);
        Assert.assertNotNull(listaItems, "La lista de items esta vacía");
    }

    public static void addItemToCar(){ //usamos un ThreadLocal para guardar la información de la descripción del Item y usarla en el proximo step
        waitVisibilities(TestingUYConstants.LISTA_ITEMS_XPATH);
        List<WebElement> listaItems = getElements(TestingUYConstants.LISTA_ITEMS_XPATH);
        hoverItem(listaItems.get(0));
        descData.set(listaItems.get(0).getAttribute("innerText").split("\n")[2]);
        click(TestingUYConstants.ADD_TO_CAR_XPATH);
    }

    public static void clickModalCompra(){
        click(TestingUYConstants.PROCED_TO_CHECKOUT_XPATH);
    }

    public static void verificarItemEnCarrito(){
        hoverItem(TestingUYConstants.CARRITO_COMPRAS_XPATH,"");
        waitVisibility(TestingUYConstants.ITEM_IN_CART_XPATH);
        Assert.assertEquals(descData.get(), getAttribute(TestingUYConstants.ITEM_IN_CART_XPATH, "title"));
    }

}
