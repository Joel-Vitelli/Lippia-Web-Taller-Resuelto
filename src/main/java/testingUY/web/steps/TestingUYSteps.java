package testingUY.web.steps;

import com.crowdar.core.PageSteps;
import io.cucumber.java.en.*;
import testingUY.web.services.TestingUYService;


public class TestingUYSteps extends PageSteps {

    @Given("Estamos en la pagina de Compras")
    public void estamosEnLaPaginaDeCompras() {
        TestingUYService.navegarWeb();
    }

    @When("seleccionamos del menu '(.*)' - '(.*)' - '(.*)'")
    public void seleccionamosDelMenu(String menu, String subMenu1, String subMenu2) {
        TestingUYService.navegarMenu(menu,subMenu2);
    }

    @Then("se visualiza el menu de las secciones navegadas '(.*)'")
    public void seVisualizaElMenuDeLasSeccionesNavegadas(String navegacion) {
        TestingUYService.verificarNavegacion(navegacion);
    }

    @And("se visualiza el encabezado de la sección con título '(.*)'")
    public void seVisualizaElEncabezadoDeLaSecciónConTítulo(String encabezado) {
        TestingUYService.verificarEncabezado(encabezado);
    }

    @And("se visualiza la lista de filtros a la izquierda con el título '(.*)'")
    public void seVisualizaLaListaDeFiltrosALaIzquierdaConElTítulo(String tituloLista) {
        TestingUYService.verificarTituloListaFiltros(tituloLista);
    }

    @And("se muestra al menos un item en la sección de productos")
    public void seMuestraAlMenosUnItemEnLaSecciónDeProductos() {
        TestingUYService.verificarItems();
    }

    @And("agregamos el primer producto de la lista al carrito haciendo click en {string}")
    public void agregamosElPrimerProductoDeLaListaAlCarritoHaciendoClickEn(String arg0) {
        TestingUYService.addItemToCar();
    }

    @Then("se verifica que se agrego el carrito aceptando la ventana pop-up que aparece")
    public void seVerificaQueSeAgregoElCarritoAceptandoLaVentanaPopUpQueAparece() {
        TestingUYService.clickModalCompra();
    }

    @And("se verifica el producto agregado desde el carrito de compras")
    public void seVerificaElProductoAgregadoDesdeElCarritoDeCompras() {
        TestingUYService.verificarItemEnCarrito();
    }

}
