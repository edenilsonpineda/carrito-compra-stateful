<%-- 
    Document   : index
    Created on : Feb 26, 2022, 12:07:52 PM
    Author     : edeni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Carrito de Compras - EJB Stateful</title>
        
        
        <!-- Compiled and minified CSS -->
        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs5/dt-1.11.4/r-2.2.9/datatables.min.css"/>

        <link href="${contextPath}/assets/css/jumbotron.css" rel="stylesheet" type="text/css"/>
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
    </head>
    <body>
        <div class="container">
            <div class="header clearfix">
                <h3 class="text-muted">Carrito de Compras</h3>
                <br>
                <b>Ingreso de Productos.</b>
            </div>


            <div class="card">
                <div class="card-body">
                    <form name="procesarCarrito" class="" action="ProcesarCompras" method="POST">
                        <div class="form-group">
                            <label for="nombreProducto">Nombre del Producto: </label>
                            <input type="text" name="nombreProducto" class="form-control" id="nombreProducto" required="true">
                        </div>

                        <div class="form-group">
                            <label for="precioProducto">Precio del Producto: </label>
                            <input type="text" name="precioProducto" class="form-control" id="precioProducto" required="true">
                        </div>

                        <div class="form-group">
                            <label for="cantidad">Cantidad: </label>
                            <input type="number" min="0" max="100" name="cantidad" class="form-control" id="cantidad" required="true">
                        </div>
                        <br>
                        
                        <div class="btn-toolbar" role="toolbar">
                            <div class="btn-group" role="group" style="margin-right: 1rem;">
                                <button type="submit" class="btn btn-success">Agregar</button>
                            </div>
                            <br>
                            <div class="btn-group" role="group">
                                <button type="reset" class="btn btn-secondary">Limpiar</button>
                            </div>
                        </div>
                    </form>
                </div>
                
            </div>
            <br>
            
            <div class="card">
                <div class="card-body">
                    <table class="table table-striped table-bordered" style="width:100%" id="tablaProductos">
                        <thead>
                            <tr>
                                <th>Producto</th>
                                <th>Precio</th>
                                <th>Cantidad</th>
                                <th>Subtotal</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.listaProductos}" var="productos">
                                <tr>
                                    <td>${productos.nombreProducto}</td>
                                    <td>${productos.precio}</td>
                                    <td>${productos.cantidad}</td>
                                    <td>${productos.subTotalDosDigitos()}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="3">TOTAL:</td>
                                <td>$ <c:out value="${requestScope.totalCompra}" default="0.00"/></td>
                            </tr>
                        </tfoot>

                    </table>
                </div>
            </div>

            <footer class="footer">
                <p>&copy; 2022 - Carrito de Compras EJB Stateful </p>
            </footer>

        </div> <!-- /container -->

        <!-- Compiled and minified JavaScript -->
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/v/bs5/dt-1.11.4/r-2.2.9/datatables.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#tablaProductos').DataTable();
            });
            
            function setTwoNumberDecimal(e) {
                this.value = parseFloat(this.value).toFixed(2);
            }
            
            <c:if test="${not empty error}">
                toastr.error('${error}');
                <c:set var="error" value="" scope="session" />
            </c:if>
                
            <c:if test="${not empty exito}">
                toastr.success('${exito}');
                <c:set var="exito" value="" scope="session" />
            </c:if>
        </script>
    </body>


</html>
