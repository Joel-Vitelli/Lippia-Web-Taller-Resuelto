package lippiaTaller.web.steps;

import com.crowdar.core.PageSteps;
import io.cucumber.java.en.*;
import lippiaTaller.web.services.LippiaTallerService;


public class LippiaTallerSteps extends PageSteps {

    @Given("Estamos en la pagina de Compras")
    public void estamosEnLaPaginaDeCompras() {
        LippiaTallerService.navegarWeb();
    }

    @When("seleccionamos del menu '(.*)' - '(.*)' - '(.*)'")
    public void seleccionamosDelMenu(String menu, String subMenu1, String subMenu2) {
        LippiaTallerService.navegarMenu(menu,subMenu2);
    }

    @Then("se visualiza el menu de las secciones navegadas '(.*)'")
    public void seVisualizaElMenuDeLasSeccionesNavegadas(String navegacion) {
        LippiaTallerService.verificarNavegacion(navegacion);
    }

    @And("se visualiza el encabezado de la sección con título '(.*)'")
    public void seVisualizaElEncabezadoDeLaSecciónConTítulo(String encabezado) {
        LippiaTallerService.verificarEncabezado(encabezado);
    }

    @And("se visualiza la lista de filtros a la izquierda con el título '(.*)'")
    public void seVisualizaLaListaDeFiltrosALaIzquierdaConElTítulo(String tituloLista) {
        LippiaTallerService.verificarTituloListaFiltros(tituloLista);
    }

    @And("se muestra al menos un item en la sección de productos")
    public void seMuestraAlMenosUnItemEnLaSecciónDeProductos() {
        LippiaTallerService.verificarItems();
    }

    @And("agregamos el primer producto de la lista al carrito haciendo click en {string}")
    public void agregamosElPrimerProductoDeLaListaAlCarritoHaciendoClickEn(String arg0) {
        LippiaTallerService.addItemToCar();
    }

    @Then("se verifica que se agrego el carrito aceptando la ventana pop-up que aparece")
    public void seVerificaQueSeAgregoElCarritoAceptandoLaVentanaPopUpQueAparece() {
        LippiaTallerService.clickModalCompra();
    }

    @And("se verifica el producto agregado desde el carrito de compras")
    public void seVerificaElProductoAgregadoDesdeElCarritoDeCompras() {
        LippiaTallerService.verificarItemEnCarrito();
    }

}
