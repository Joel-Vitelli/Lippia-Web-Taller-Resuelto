@Tag
Feature: E-commerce de prueba

  @Smoke
  #OPCIONAL
  Scenario: Navegar al menu "Women"-"Tops"-"T-Shirts"
    Given Estamos en la pagina de Compras
    When seleccionamos del menu 'Women' - 'Tops' - 'T-shirts'
    Then se visualiza el menu de las secciones navegadas 'Home> Women>Tops>T-shirts'
    And se visualiza el encabezado de la sección con título 'T-shirts'
    And se visualiza la lista de filtros a la izquierda con el título 'CATALOG'
    And se muestra al menos un item en la sección de productos

  @Smoke @Taller
  Scenario Outline: Navegar al menu "<Menu>"-"<submenu1>"-"<submenu2>"
    Given Estamos en la pagina de Compras
    When seleccionamos del menu '<menu>' - '<submenu1>' - '<submenu2>'
    Then se visualiza el menu de las secciones navegadas 'Home> <menu>><submenu1>><submenu2>'
    And se visualiza el encabezado de la sección con título '<submenu2>'
    And se visualiza la lista de filtros a la izquierda con el título 'CATALOG'
    And se muestra al menos un item en la sección de productos

    Examples:
      | menu  | submenu1 | submenu2        |
      | Women | Tops     | T-shirts        |
      | Women | Tops     | Blouses         |
      | Women | Dresses  | Evening Dresses |

  @Smoke
  #OPCIONAL
  Scenario Outline: Agregar producto al carrito exitosamente
    Given Estamos en la pagina de Compras
    When seleccionamos del menu '<menu>' - '<submenu1>' - '<submenu2>'
    And agregamos el primer producto de la lista al carrito haciendo click en "Add to Cart"
    #La idea es quedarse con la descripcion del producto para luego validar la existencia del mismo en la segunda aserción
    Then se verifica que se agrego el carrito aceptando la ventana pop-up que aparece
    And se verifica el producto agregado desde el carrito de compras

    Examples:
      | menu  | submenu1 | submenu2        |
      | Women | Tops     | T-shirts        |
      | Women | Tops     | Blouses         |
      | Women | Dresses  | Evening Dresses |
