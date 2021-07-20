-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-04-2021 a las 23:56:19
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbventas`
--
CREATE DATABASE IF NOT EXISTS `dbventas` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `dbventas`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `SP_I_Categoria`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_Categoria` (`pdescripcion` VARCHAR(100))  BEGIN		
		INSERT INTO categoria(descripcion)
		VALUES(pdescripcion);
	END$$

DROP PROCEDURE IF EXISTS `SP_I_Cliente`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_Cliente` (IN `pnombre` VARCHAR(100), IN `prol` VARCHAR(12), IN `prut` VARCHAR(12), IN `pdireccion` VARCHAR(50), IN `ptelefono` VARCHAR(15), IN `pobsv` TEXT)  BEGIN		
		INSERT INTO cliente(nombre,rol,rut,direccion,telefono,obsv)
		VALUES(pnombre,prol,prut,pdireccion,ptelefono,pobsv);
	END$$

DROP PROCEDURE IF EXISTS `SP_I_Compra`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_Compra` (IN `pidtipodocumento` INT, IN `pidproveedor` INT, IN `pidempleado` INT, IN `pnumero` VARCHAR(20), IN `pfecha` DATE, IN `psubtotal` INT, IN `piva` INT, IN `ptotal` INT, IN `pestado` VARCHAR(30))  BEGIN		
		INSERT INTO compra(idtipodocumento,idproveedor,idempleado,numero,fecha,subtotal,iva,total,estado)
		VALUES(pidtipodocumento,pidproveedor,pidempleado,pnumero,pfecha,psubtotal,piva,ptotal,pestado);
	END$$

DROP PROCEDURE IF EXISTS `SP_I_DetalleCompra`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_DetalleCompra` (IN `pidcompra` INT, IN `pidproducto` INT, IN `pcantidad` INT, IN `pprecio` INT, IN `ptotal` INT)  BEGIN		
		INSERT INTO detallecompra(idcompra,idproducto,cantidad,precio,total)
		VALUES(pidcompra,pidproducto,pcantidad,pprecio,ptotal);
	END$$

DROP PROCEDURE IF EXISTS `SP_I_DetalleVenta`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_DetalleVenta` (IN `pidventa` INT, IN `pidproducto` INT, IN `pcantidad` INT, IN `pcosto` INT, IN `pprecio` INT, IN `ptotal` INT)  BEGIN		
		INSERT INTO detalleventa(idventa,idproducto,cantidad,costo,precio,total)
		VALUES(pidventa,pidproducto,pcantidad,pcosto,pprecio,ptotal);
	END$$

DROP PROCEDURE IF EXISTS `SP_I_Empleado`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_Empleado` (IN `pnombre` VARCHAR(50), IN `papellido` VARCHAR(80), IN `psexo` VARCHAR(1), IN `pfechanac` DATE, IN `pdireccion` VARCHAR(100), IN `ptelefono` VARCHAR(10), IN `pcelular` VARCHAR(15), IN `pemail` VARCHAR(80), IN `prut` VARCHAR(12), IN `pfechaing` DATE, IN `psueldo` INT, IN `pestado` VARCHAR(30), IN `pusuario` VARCHAR(20), IN `pcontraseña` TEXT, IN `pidtipousuario` INT)  BEGIN		
		INSERT INTO empleado(nombre,apellido,sexo,fechanac,direccion,telefono,celular,email,rut,fechaing,sueldo,estado,usuario,contraseña,idtipousuario)
		VALUES(pnombre,papellido,psexo,pfechanac,pdireccion,ptelefono,pcelular,pemail,prut,pfechaing,psueldo,pestado,pusuario,pcontraseña,pidtipousuario);
	END$$

DROP PROCEDURE IF EXISTS `SP_I_Producto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_Producto` (IN `pcodigo` VARCHAR(50), IN `pnombre` VARCHAR(100), IN `pdescripcion` TEXT, IN `pstock` INT, IN `pstockmin` INT, IN `ppreciocosto` INT, IN `pprecioventa` INT, IN `putilidad` INT, IN `pestado` VARCHAR(30), IN `pidcategoria` INT)  BEGIN		
		INSERT INTO producto(codigo,nombre,descripcion,stock,stockmin,preciocosto,precioventa,utilidad,estado,idcategoria)
		VALUES(pcodigo,pnombre,pdescripcion,pstock,pstockmin,ppreciocosto,pprecioventa,putilidad,pestado,pidcategoria);
	END$$

DROP PROCEDURE IF EXISTS `SP_I_Proveedor`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_Proveedor` (IN `pnombre` VARCHAR(100), IN `prol` VARCHAR(12), IN `prut` VARCHAR(12), IN `pdireccion` VARCHAR(100), IN `ptelefono` VARCHAR(10), IN `pcelular` VARCHAR(15), IN `pemail` VARCHAR(80), IN `pcuenta1` VARCHAR(50), IN `pcuenta2` VARCHAR(50), IN `pestado` VARCHAR(30), IN `pobsv` TEXT)  BEGIN		
		INSERT INTO proveedor(nombre,rol,rut,direccion,telefono,celular,email,cuenta1,cuenta2,estado,obsv)
		VALUES(pnombre,prol,prut,pdireccion,ptelefono,pcelular,pemail,pcuenta1,pcuenta2,pestado,pobsv);
	END$$

DROP PROCEDURE IF EXISTS `SP_I_TipoDocumento`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_TipoDocumento` (`pdescripcion` VARCHAR(80))  BEGIN		
		INSERT INTO tipodocumento(descripcion)
		VALUES(pdescripcion);
	END$$

DROP PROCEDURE IF EXISTS `SP_I_TipoUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_TipoUsuario` (`pdescripcion` VARCHAR(80), `pp_venta` INT, `pp_compra` INT, `pp_producto` INT, `pp_proveedor` INT, `pp_empleado` INT, `pp_cliente` INT, `pp_categoria` INT, `pp_tipodoc` INT, `pp_tipouser` INT, `pp_anularv` INT, `pp_anularc` INT, `pp_estadoprod` INT, `pp_ventare` INT, `pp_ventade` INT, `pp_estadistica` INT, `pp_comprare` INT, `pp_comprade` INT, `pp_pass` INT, `pp_respaldar` INT, `pp_restaurar` INT, `pp_caja` INT)  BEGIN		
		INSERT INTO tipousuario(descripcion,p_venta,p_compra,p_producto,p_proveedor,p_empleado,p_cliente,p_categoria,p_tipodoc,p_tipouser,p_anularv,p_anularc,
		p_estadoprod,p_ventare,p_ventade,p_estadistica,p_comprare,p_comprade,p_pass,p_respaldar,p_restaurar,p_caja)
		VALUES(pdescripcion,pp_venta,pp_compra,pp_producto,pp_proveedor,pp_empleado,pp_cliente,pp_categoria,pp_tipodoc,pp_tipouser,pp_anularv,pp_anularc,
		pp_estadoprod,pp_ventare,pp_ventade,pp_estadistica,pp_comprare,pp_comprade,pp_pass,pp_respaldar,pp_restaurar,pp_caja);
	END$$

DROP PROCEDURE IF EXISTS `SP_I_Venta`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_I_Venta` (IN `pidtipodocumento` INT, IN `pidcliente` INT, IN `pidempleado` INT, IN `pserie` VARCHAR(5), IN `pnumero` VARCHAR(20), IN `pfecha` DATE, IN `ptotalventa` INT, IN `pdescuento` INT, IN `psubtotal` INT, IN `piva` INT, IN `ptotalpagar` INT, IN `pestado` VARCHAR(30))  BEGIN		
		INSERT INTO venta(idtipodocumento,idcliente,idempleado,serie,numero,fecha,totalventa,descuento,subtotal,iva,totalpagar,estado)
		VALUES(pidtipodocumento,pidcliente,pidempleado,pserie,pnumero,pfecha,ptotalventa,pdescuento,psubtotal,piva,ptotalpagar,pestado);
	END$$

DROP PROCEDURE IF EXISTS `SP_S_Categoria`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Categoria` ()  BEGIN
		SELECT * FROM categoria;
	END$$

DROP PROCEDURE IF EXISTS `SP_S_CategoriaPorParametro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_CategoriaPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(20))  BEGIN
	IF pcriterio = "id" THEN
		SELECT c.IdCategoria,c.Descripcion FROM categoria AS c WHERE c.IdCategoria=pbusqueda;
	ELSEIF pcriterio = "descripcion" THEN
		SELECT c.IdCategoria,c.Descripcion FROM categoria AS c WHERE c.Descripcion LIKE CONCAT("%",pbusqueda,"%");
	ELSE
		SELECT c.IdCategoria,c.Descripcion FROM categoria AS c;
	END IF; 
	END$$

DROP PROCEDURE IF EXISTS `SP_S_Cliente`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Cliente` ()  BEGIN
		SELECT * FROM cliente;
	END$$

DROP PROCEDURE IF EXISTS `SP_S_ClientePorParametro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_ClientePorParametro` (IN `pcriterio` VARCHAR(20), IN `pbusqueda` VARCHAR(20))  BEGIN
	IF pcriterio = "id" THEN
		SELECT c.IdCliente,c.Nombre,c.Rol,c.Rut,c.Direccion,c.Telefono,c.Obsv FROM cliente AS c WHERE c.IdCliente=pbusqueda;
	ELSEIF pcriterio = "nombre" THEN
		SELECT c.IdCliente,c.Nombre,c.Rol,c.Rut,c.Direccion,c.Telefono,c.Obsv FROM cliente AS c WHERE c.Nombre LIKE CONCAT("%",pbusqueda,"%");
	ELSEIF pcriterio = "rol" THEN
		SELECT c.IdCliente,c.Nombre,c.Rol,c.Rut,c.Direccion,c.Telefono,c.Obsv FROM cliente AS c WHERE c.Ruc LIKE CONCAT("%",pbusqueda,"%");
   ELSEIF pcriterio = "rut" THEN
		SELECT c.IdCliente,c.Nombre,c.Rol,c.Rut,c.Direccion,c.Telefono,c.Obsv FROM cliente AS c WHERE c.Dni LIKE CONCAT("%",pbusqueda,"%");
	ELSE
		SELECT c.IdCliente,c.Nombre,c.Rol,c.Rut,c.Direccion,c.Telefono,c.Obsv FROM cliente AS c;
	END IF; 
	END$$

DROP PROCEDURE IF EXISTS `SP_S_Compra`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Compra` ()  BEGIN
		SELECT c.IdCompra,td.Descripcion AS TipoDocumento,p.Nombre AS Proveedor,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,c.Numero,c.Fecha,c.SubTotal,c.Iva,c.Total,c.Estado
		FROM compra AS c
		INNER JOIN tipodocumento AS td ON c.IdTipoDocumento=td.IdTipoDocumento	 
		INNER JOIN proveedor AS p ON c.IdProveedor=p.IdProveedor		
		INNER JOIN empleado AS e ON c.IdEmpleado=e.IdEmpleado
		ORDER BY c.IdCompra;
	END$$

DROP PROCEDURE IF EXISTS `SP_S_CompraPorDetalle`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_CompraPorDetalle` (`pcriterio` VARCHAR(30), `pfechaini` DATE, `pfechafin` DATE)  BEGIN
		IF pcriterio = "consultar" THEN
			SELECT p.Codigo,p.Nombre AS Producto,ca.Descripcion AS Categoria,dc.Precio,
			SUM(dc.Cantidad) AS Cantidad,SUM(dc.Total) AS Total FROM compra AS c
			INNER JOIN detallecompra AS dc ON c.IdCompra=dc.IdCompra
			INNER JOIN producto AS p ON dc.IdProducto=p.IdProducto
			INNER JOIN categoria AS ca ON p.IdCategoria=ca.IdCategoria
			WHERE (c.Fecha>=pfechaini AND c.Fecha<=pfechafin) AND c.Estado="NORMAL" GROUP BY p.IdProducto
			ORDER BY c.IdCompra DESC;
		END IF;

	END$$

DROP PROCEDURE IF EXISTS `SP_S_CompraPorFecha`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_CompraPorFecha` (`pcriterio` VARCHAR(30), `pfechaini` DATE, `pfechafin` DATE, `pdocumento` VARCHAR(30))  BEGIN
		IF pcriterio = "anular" THEN
			SELECT c.IdCompra,p.Nombre AS Proveedor,c.Fecha,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,td.Descripcion AS TipoDocumento,c.Numero,
			c.Estado,c.Total FROM compra AS c
			INNER JOIN tipodocumento AS td ON c.IdTipoDocumento=td.IdTipoDocumento
			INNER JOIN proveedor AS p ON c.IdProveedor=p.IdProveedor
			INNER JOIN empleado AS e ON c.IdEmpleado=e.IdEmpleado
			WHERE (c.Fecha>=pfechaini AND c.Fecha<=pfechafin) AND td.Descripcion=pdocumento ORDER BY c.IdCompra DESC;
		ELSEIF pcriterio = "consultar" THEN
		   SELECT c.IdCompra,p.Nombre AS Proveedor,c.Fecha,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,td.Descripcion AS TipoDocumento,c.Numero,
			c.Estado,c.Total FROM compra AS c
			INNER JOIN tipodocumento AS td ON c.IdTipoDocumento=td.IdTipoDocumento
			INNER JOIN proveedor AS p ON c.IdProveedor=p.IdProveedor
			INNER JOIN empleado AS e ON c.IdEmpleado=e.IdEmpleado
			WHERE c.Fecha>=pfechaini AND c.Fecha<=pfechafin ORDER BY c.IdCompra DESC;
		END IF;

	END$$

DROP PROCEDURE IF EXISTS `SP_S_DetalleCompra`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_DetalleCompra` ()  BEGIN
		SELECT * FROM detallecompra;
	END$$

DROP PROCEDURE IF EXISTS `SP_S_DetalleCompraPorParametro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_DetalleCompraPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(20))  BEGIN
			IF pcriterio = "id" THEN
				SELECT dc.IdCompra,p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,dc.Cantidad,dc.Precio,dc.Total  FROM detallecompra AS dc
				INNER JOIN producto AS p ON dc.IdProducto=p.IdProducto
				WHERE dc.IdCompra=pbusqueda ORDER BY dc.IdCompra;
			
			END IF; 
			
	END$$

DROP PROCEDURE IF EXISTS `SP_S_DetalleVenta`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_DetalleVenta` ()  BEGIN
		SELECT * FROM detalleventa;
	END$$

DROP PROCEDURE IF EXISTS `SP_S_DetalleVentaPorParametro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_DetalleVentaPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(20))  BEGIN
			IF pcriterio = "id" THEN
				SELECT dv.IdVenta,p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,dv.Cantidad,dv.Precio,dv.Total  FROM detalleventa AS dv
				INNER JOIN producto AS p ON dv.IdProducto=p.IdProducto
				WHERE dv.IdVenta=pbusqueda ORDER BY dv.IdVenta;
			
			END IF; 
			
	END$$

DROP PROCEDURE IF EXISTS `SP_S_Empleado`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Empleado` ()  BEGIN
		SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Rut,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.`Contraseña`,tu.Descripcion AS TipoUsuario
		FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario=tu.IdTipoUsuario
		ORDER BY e.IdEmpleado;
	END$$

DROP PROCEDURE IF EXISTS `SP_S_EmpleadoPorParametro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_EmpleadoPorParametro` (IN `pcriterio` VARCHAR(20), IN `pbusqueda` VARCHAR(20))  BEGIN
	IF pcriterio = "id" THEN
		SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Rut,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.`Contraseña`,tu.Descripcion AS TipoUsuario
		FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario = tu.IdTipoUsuario 
		WHERE e.IdEmpleado=pbusqueda;
	ELSEIF pcriterio = "nombre" THEN
		SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Rut,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.`Contraseña`,tu.Descripcion AS TipoUsuario
		FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario = tu.IdTipoUsuario 
		WHERE ((e.Nombre LIKE CONCAT("%",pbusqueda,"%")) OR (e.Apellido LIKE CONCAT("%",pbusqueda,"%")));
	ELSEIF pcriterio = "rut" THEN
		SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Rut,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.`Contraseña`,tu.Descripcion AS TipoUsuario
		FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario = tu.IdTipoUsuario 
		WHERE e.Rut LIKE CONCAT("%",pbusqueda,"%");
	ELSE
	   SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Rut,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.`Contraseña`,tu.Descripcion AS TipoUsuario
		FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario = tu.IdTipoUsuario;
	END IF; 
	END$$

DROP PROCEDURE IF EXISTS `SP_S_Login`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Login` (IN `pusuario` VARCHAR(20), IN `pcontraseña` TEXT, IN `pdescripcion` VARCHAR(80))  BEGIN
	
		SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Rut,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.`Contraseña`,tu.Descripcion
		FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario = tu.IdTipoUsuario WHERE e.Usuario = pusuario AND e.`Contraseña` = pcontraseña AND tu.Descripcion=pdescripcion;
		
	END$$

DROP PROCEDURE IF EXISTS `SP_S_LoginPermisos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_LoginPermisos` (`pnombre_usuario` VARCHAR(20), `pdescripcion_tipousuario` VARCHAR(80))  BEGIN
	
		SELECT tu.IdTipoUsuario,e.Usuario,tu.Descripcion,tu.p_venta,tu.p_compra,tu.p_producto,tu.p_proveedor,tu.p_empleado,tu.p_cliente,tu.p_categoria,tu.p_tipodoc,tu.p_tipouser,
		tu.p_anularv,tu.p_anularc,tu.p_estadoprod,tu.p_ventare,tu.p_ventade,tu.p_estadistica,tu.p_comprare,tu.p_comprade,tu.p_pass,tu.p_respaldar,tu.p_restaurar,tu.p_caja
		FROM tipousuario AS tu INNER JOIN empleado AS e ON tu.IdTipoUsuario = e.IdTipoUsuario WHERE e.Usuario = pnombre_usuario AND tu.Descripcion=pdescripcion_tipousuario;
		
	END$$

DROP PROCEDURE IF EXISTS `SP_S_Producto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Producto` ()  BEGIN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria=c.IdCategoria
		ORDER BY p.IdProducto;
	END$$

DROP PROCEDURE IF EXISTS `SP_S_ProductoActivo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_ProductoActivo` ()  BEGIN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria=c.IdCategoria WHERE p.Estado="Activo"
		ORDER BY p.IdProducto;
	END$$

DROP PROCEDURE IF EXISTS `SP_S_ProductoActivoPorParametro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_ProductoActivoPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(50))  BEGIN
	IF pcriterio = "id" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.IdProducto=pbusqueda AND p.Estado="Activo";
 	ELSEIF pcriterio = "codigo" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Codigo=pbusqueda AND p.Estado="Activo";
	ELSEIF pcriterio = "nombre" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Nombre LIKE CONCAT("%",pbusqueda,"%") AND p.Estado="Activo";
	ELSEIF pcriterio = "descripcion" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Descripcion LIKE CONCAT("%",pbusqueda,"%") AND p.Estado="Activo";
	ELSEIF pcriterio = "categoria" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE c.Descripcion LIKE CONCAT("%",pbusqueda,"%") AND p.Estado="Activo";
	ELSE
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria WHERE p.Estado="Activo";
	END IF; 
	END$$

DROP PROCEDURE IF EXISTS `SP_S_ProductoPorParametro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_ProductoPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(50))  BEGIN
	IF pcriterio = "id" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.IdProducto=pbusqueda;
	ELSEIF pcriterio = "codigo" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Codigo=pbusqueda;
	ELSEIF pcriterio = "nombre" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Nombre LIKE CONCAT("%",pbusqueda,"%");
	ELSEIF pcriterio = "descripcion" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Descripcion LIKE CONCAT("%",pbusqueda,"%");
	ELSEIF pcriterio = "categoria" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE c.Descripcion LIKE CONCAT("%",pbusqueda,"%");
	ELSE
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria;
	END IF; 
	END$$

DROP PROCEDURE IF EXISTS `SP_S_ProductoVerificarCodigoBar`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_ProductoVerificarCodigoBar` (`pbusqueda` VARCHAR(50))  BEGIN
	
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Codigo=pbusqueda;

	END$$

DROP PROCEDURE IF EXISTS `SP_S_Proveedor`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Proveedor` ()  BEGIN
		SELECT * FROM proveedor;
	END$$

DROP PROCEDURE IF EXISTS `SP_S_ProveedorPorParametro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_ProveedorPorParametro` (IN `pcriterio` VARCHAR(20), IN `pbusqueda` VARCHAR(20))  BEGIN
	IF pcriterio = "id" THEN
		SELECT p.IdProveedor,p.Nombre,p.Rol,p.Rut,p.Direccion,p.Telefono,p.Celular,p.Email,p.Cuenta1,p.Cuenta2,p.Estado,p.Obsv FROM proveedor AS p WHERE p.IdProveedor=pbusqueda;
	ELSEIF pcriterio = "nombre" THEN
		SELECT p.IdProveedor,p.Nombre,p.Rol,p.Rut,p.Direccion,p.Telefono,p.Celular,p.Email,p.Cuenta1,p.Cuenta2,p.Estado,p.Obsv FROM proveedor AS p WHERE p.Nombre LIKE CONCAT("%",pbusqueda,"%");
	ELSEIF pcriterio = "rol" THEN
		SELECT p.IdProveedor,p.Nombre,p.Rol,p.Rut,p.Direccion,p.Telefono,p.Celular,p.Email,p.Cuenta1,p.Cuenta2,p.Estado,p.Obsv FROM proveedor AS p WHERE p.Rol LIKE CONCAT("%",pbusqueda,"%");
   ELSEIF pcriterio = "rut" THEN
		SELECT p.IdProveedor,p.Nombre,p.Rol,p.Rut,p.Direccion,p.Telefono,p.Celular,p.Email,p.Cuenta1,p.Cuenta2,p.Estado,p.Obsv FROM proveedor AS p WHERE p.Rut LIKE CONCAT("%",pbusqueda,"%");
	ELSE
		SELECT p.IdProveedor,p.Nombre,p.Rol,p.Rut,p.Direccion,p.Telefono,p.Celular,p.Email,p.Cuenta1,p.Cuenta2,p.Estado,p.Obsv FROM proveedor AS p;
	END IF; 
	END$$

DROP PROCEDURE IF EXISTS `SP_S_TipoDocumento`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_TipoDocumento` ()  BEGIN
		SELECT * FROM tipodocumento;
	END$$

DROP PROCEDURE IF EXISTS `SP_S_TipoDocumentoPorParametro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_TipoDocumentoPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(20))  BEGIN
	IF pcriterio = "id" THEN
		SELECT td.IdTipoDocumento,td.Descripcion FROM tipodocumento AS td WHERE td.IdTipoDocumento=pbusqueda;
	ELSEIF pcriterio = "descripcion" THEN
		SELECT td.IdTipoDocumento,td.Descripcion FROM tipodocumento AS td WHERE td.Descripcion LIKE CONCAT("%",pbusqueda,"%");
	ELSE
		SELECT td.IdTipoDocumento,td.Descripcion FROM tipodocumento AS td;
	END IF; 
	END$$

DROP PROCEDURE IF EXISTS `SP_S_TipoUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_TipoUsuario` ()  BEGIN
		SELECT * FROM tipousuario;
	END$$

DROP PROCEDURE IF EXISTS `SP_S_TipoUsuarioPorParametro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_TipoUsuarioPorParametro` (`pcriterio` VARCHAR(20), `pbusqueda` VARCHAR(20))  BEGIN
	IF pcriterio = "id" THEN
		SELECT * FROM tipousuario AS tp WHERE tp.IdTipoUsuario=pbusqueda;
	ELSEIF pcriterio = "descripcion" THEN
		SELECT * FROM tipousuario AS tp WHERE tp.Descripcion LIKE CONCAT("%",pbusqueda,"%");
	ELSE
		SELECT * FROM tipousuario AS tp;
	END IF; 
	END$$

DROP PROCEDURE IF EXISTS `SP_S_UltimoIdCompra`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_UltimoIdCompra` ()  BEGIN
		SELECT MAX(IdCompra) AS id FROM compra;
	END$$

DROP PROCEDURE IF EXISTS `SP_S_UltimoIdVenta`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_UltimoIdVenta` ()  BEGIN
		SELECT MAX(IdVenta) AS id FROM venta;
	END$$

DROP PROCEDURE IF EXISTS `SP_S_Venta`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Venta` ()  BEGIN
		SELECT v.IdVenta,td.Descripcion AS TipoDocumento,c.Nombre AS Cliente,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,v.Serie,v.Numero,v.Fecha,v.TotalVenta,v.Descuento,v.SubTotal,
		v.Iva,v.TotalPagar,v.Estado
		FROM venta AS v 
		INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
		INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
		INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
		ORDER BY v.IdVenta;
	END$$

DROP PROCEDURE IF EXISTS `SP_S_VentaMensual`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_VentaMensual` (`pcriterio` VARCHAR(20), `pfecha_ini` VARCHAR(20), `pfecha_fin` VARCHAR(20))  BEGIN
			IF pcriterio = "consultar" THEN
				SELECT CONCAT(UPPER(MONTHNAME(v.Fecha))," ",YEAR(v.Fecha)) AS Fecha,SUM(v.TotalVenta) AS Total,
				ROUND((SUM(v.TotalVenta)*100)/(SELECT SUM(v.TotalPagar) AS TotalVenta FROM venta AS v WHERE ((date_format(v.Fecha,'%Y-%m') >= pfecha_ini) AND (date_format(v.Fecha,'%Y-%m') <= pfecha_fin)) AND v.Estado="EMITIDO")) AS Porcentaje
				FROM venta AS v
				WHERE ((date_format(v.Fecha,'%Y-%m') >= pfecha_ini) AND (date_format(v.Fecha,'%Y-%m') <= pfecha_fin)) AND v.Estado="EMITIDO" GROUP BY v.Fecha;			
								
			END IF; 
			

	END$$

DROP PROCEDURE IF EXISTS `SP_S_VentaPorDetalle`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_VentaPorDetalle` (`pcriterio` VARCHAR(30), `pfechaini` DATE, `pfechafin` DATE)  BEGIN
		IF pcriterio = "consultar" THEN
			SELECT p.Codigo,p.Nombre AS Producto,c.Descripcion AS Categoria,dv.Costo,dv.Precio,
			SUM(dv.Cantidad) AS Cantidad,SUM(dv.Total) AS Total,
			SUM(TRUNCATE((Total-(dv.Costo*dv.Cantidad)),2)) AS Ganancia FROM venta AS v
			INNER JOIN detalleventa AS dv ON v.IdVenta=dv.IdVenta
			INNER JOIN producto AS p ON dv.IdProducto=p.IdProducto
			INNER JOIN categoria AS c ON p.IdCategoria=c.IdCategoria
			WHERE (v.Fecha>=pfechaini AND v.Fecha<=pfechafin) AND v.Estado="EMITIDO" GROUP BY p.IdProducto
			ORDER BY v.IdVenta DESC;
		END IF;

	END$$

DROP PROCEDURE IF EXISTS `SP_S_VentaPorFecha`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_VentaPorFecha` (`pcriterio` VARCHAR(30), `pfechaini` DATE, `pfechafin` DATE, `pdocumento` VARCHAR(30))  BEGIN
		IF pcriterio = "anular" THEN
			SELECT v.IdVenta,c.Nombre AS Cliente,v.Fecha,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,td.Descripcion AS TipoDocumento,v.Serie,v.Numero,
			v.Estado,v.TotalPagar  FROM venta AS v
			INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
			INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
			INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
			WHERE (v.Fecha>=pfechaini AND v.Fecha<=pfechafin) AND td.Descripcion=pdocumento ORDER BY v.IdVenta DESC;
		ELSEIF pcriterio = "consultar" THEN
		   SELECT v.IdVenta,c.Nombre AS Cliente,v.Fecha,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,td.Descripcion AS TipoDocumento,v.Serie,v.Numero,
			v.Estado,v.TotalVenta,v.Descuento,v.TotalPagar  FROM venta AS v
			INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
			INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
			INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
			WHERE (v.Fecha>=pfechaini AND v.Fecha<=pfechafin) ORDER BY v.IdVenta DESC;
		ELSEIF pcriterio = "caja" THEN	
		   SELECT SUM(dv.Cantidad) AS Cantidad,p.Nombre AS Producto,dv.Precio,
			SUM(dv.Total) AS Total, SUM(TRUNCATE((Total-(dv.Costo*dv.Cantidad)),2)) AS Ganancia,v.Fecha FROM venta AS v
			INNER JOIN detalleventa AS dv ON v.IdVenta=dv.IdVenta
			INNER JOIN producto AS p ON dv.IdProducto=p.IdProducto
			INNER JOIN categoria AS c ON p.IdCategoria=c.IdCategoria
			WHERE v.Fecha=pfechaini AND v.Estado="EMITIDO" GROUP BY p.IdProducto
			ORDER BY v.IdVenta DESC;
		END IF;

	END$$

DROP PROCEDURE IF EXISTS `SP_S_VentaPorParametro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_VentaPorParametro` (IN `pcriterio` VARCHAR(20), IN `pbusqueda` VARCHAR(20))  BEGIN
			IF pcriterio = "id" THEN
				SELECT v.IdVenta,td.Descripcion AS TipoDocumento,c.Nombre AS Cliente,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,v.Serie,v.Numero,v.Fecha,v.TotalVenta,v.Descuento,v.SubTotal,
				v.Iva,v.TotalPagar,v.Estado  FROM venta AS v
				INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
				INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
				INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
				WHERE v.IdVenta=pbusqueda ORDER BY v.IdVenta;
			ELSEIF pcriterio = "documento" THEN
				SELECT v.IdVenta,td.Descripcion AS TipoDocumento,c.Nombre AS Cliente,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,v.Serie,v.Numero,v.Fecha,v.TotalVenta,v.Descuento,v.SubTotal,
				v.Iva,v.TotalPagar,v.Estado  FROM venta AS v
				INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
				INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
				INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
				WHERE td.Descripcion=pbusqueda ORDER BY v.IdVenta;
			END IF; 
			

	END$$

DROP PROCEDURE IF EXISTS `SP_S_Venta_DetallePorParametro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_S_Venta_DetallePorParametro` (IN `pcriterio` VARCHAR(20), IN `pbusqueda` VARCHAR(20))  BEGIN
			IF pcriterio = "id" THEN
				SELECT v.IdVenta,td.Descripcion AS TipoDocumento,c.Nombre AS Cliente,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,v.Serie,v.Numero,v.Fecha,v.TotalVenta,v.Descuento,v.SubTotal,
				v.Iva,v.TotalPagar,v.Estado,p.Codigo,p.Nombre,dv.Cantidad,p.PrecioVenta,dv.Total  FROM venta AS v
				INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
				INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
				INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
				INNER JOIN detalleventa AS dv ON v.IdVenta=dv.IdVenta
				INNER JOIN producto AS p ON dv.IdProducto=p.IdProducto
				WHERE v.IdVenta=pbusqueda ORDER BY v.IdVenta;
			
			END IF; 
			

	END$$

DROP PROCEDURE IF EXISTS `SP_U_ActualizarCompraEstado`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_ActualizarCompraEstado` (`pidcompra` INT, `pestado` VARCHAR(30))  BEGIN
		UPDATE compra SET
			estado=pestado
		WHERE idcompra = pidcompra;
	END$$

DROP PROCEDURE IF EXISTS `SP_U_ActualizarProductoStock`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_ActualizarProductoStock` (IN `pidproducto` INT, IN `pstock` INT)  BEGIN
		UPDATE producto SET
			stock=pstock
		WHERE idproducto = pidproducto;
	END$$

DROP PROCEDURE IF EXISTS `SP_U_ActualizarVentaEstado`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_ActualizarVentaEstado` (`pidventa` INT, `pestado` VARCHAR(30))  BEGIN
		UPDATE venta SET
			estado=pestado
		WHERE idventa = pidventa;
	END$$

DROP PROCEDURE IF EXISTS `SP_U_CambiarPass`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_CambiarPass` (`pidempleado` INT, `pcontraseña` TEXT)  BEGIN
		UPDATE empleado SET
			contraseña=pcontraseña
		WHERE idempleado = pidempleado;
	END$$

DROP PROCEDURE IF EXISTS `SP_U_Categoria`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_Categoria` (`pidcategoria` INT, `pdescripcion` VARCHAR(100))  BEGIN
		UPDATE categoria SET
			descripcion=pdescripcion	
		WHERE idcategoria = pidcategoria;
	END$$

DROP PROCEDURE IF EXISTS `SP_U_Cliente`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_Cliente` (IN `pidcliente` INT, IN `pnombre` VARCHAR(100), IN `prol` VARCHAR(12), IN `prut` VARCHAR(12), IN `pdireccion` VARCHAR(50), IN `ptelefono` VARCHAR(15), IN `pobsv` TEXT)  BEGIN
		UPDATE cliente SET
			nombre=pnombre,
			rol=prol,
			rut=prut,
			direccion=pdireccion,
			telefono=ptelefono,
			obsv=pobsv
		WHERE idcliente = pidcliente;
	END$$

DROP PROCEDURE IF EXISTS `SP_U_Compra`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_Compra` (IN `pidcompra` INT, IN `pidtipodocumento` INT, IN `pidproveedor` INT, IN `pidempleado` INT, IN `pnumero` VARCHAR(20), IN `pfecha` DATE, IN `psubtotal` INT, IN `piva` INT, IN `ptotal` INT, IN `pestado` VARCHAR(30))  BEGIN
		UPDATE compra SET
			idtipodocumento=pidtipodocumento,
			idproveedor=pidproveedor,
			idempleado=pidempleado,
			numero=pnumero,
			fecha=pfecha,
			subtotal=psubtotal,
			iva=piva,
			total=ptotal,
			estado=pestado
		WHERE idcompra = pidcompra;
	END$$

DROP PROCEDURE IF EXISTS `SP_U_DetalleCompra`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_DetalleCompra` (IN `pidcompra` INT, IN `pidproducto` INT, IN `pcantidad` INT, IN `pprecio` INT, IN `ptotal` INT)  BEGIN
		UPDATE venta SET
			idcompra=pidcompra,
			idproducto=pidproducto,
			cantidad=pcantidad,
			precio=pprecio,
			total=ptotal
		WHERE idcompra = pidcompra;
	END$$

DROP PROCEDURE IF EXISTS `SP_U_DetalleVenta`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_DetalleVenta` (IN `pidventa` INT, IN `pidproducto` INT, IN `pcantidad` INT, IN `pcosto` INT, IN `pprecio` INT, IN `ptotal` INT)  BEGIN
		UPDATE venta SET
			idventa=pidventa,
			idproducto=pidproducto,
			cantidad=pcantidad,
			costo=pcosto,
			precio=pprecio,
			total=ptotal
		WHERE idventa = pidventa;
	END$$

DROP PROCEDURE IF EXISTS `SP_U_Empleado`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_Empleado` (IN `pidempleado` INT, IN `pnombre` VARCHAR(50), IN `papellido` VARCHAR(80), IN `psexo` VARCHAR(1), IN `pfechanac` DATE, IN `pdireccion` VARCHAR(100), IN `ptelefono` VARCHAR(10), IN `pcelular` VARCHAR(15), IN `pemail` VARCHAR(80), IN `prut` VARCHAR(12), IN `pfechaing` DATE, IN `psueldo` INT, IN `pestado` VARCHAR(30), IN `pusuario` VARCHAR(20), IN `pcontraseña` TEXT, IN `pidtipousuario` INT)  BEGIN
		UPDATE empleado SET
			nombre=pnombre,
			apellido=papellido,
			sexo=psexo,
			fechanac=pfechanac,
			direccion=pdireccion,
			telefono=ptelefono,
			celular=pcelular,
			email=pemail,
			rut=prut,
			fechaing=pfechaing,
			sueldo=psueldo,
			estado=pestado,
			usuario=pusuario,
			contraseña=pcontraseña,
			idtipousuario=pidtipousuario			
		WHERE idempleado = pidempleado;
	END$$

DROP PROCEDURE IF EXISTS `SP_U_Producto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_Producto` (IN `pidproducto` INT, IN `pcodigo` VARCHAR(50), IN `pnombre` VARCHAR(100), IN `pdescripcion` TEXT, IN `pstock` INT, IN `pstockmin` INT, IN `ppreciocosto` INT, IN `pprecioventa` INT, IN `putilidad` INT, IN `pestado` VARCHAR(30), IN `pidcategoria` INT)  BEGIN
		UPDATE producto SET
			codigo=pcodigo,
			nombre=pnombre,
			descripcion=pdescripcion,
			stock=pstock,
			stockmin=pstockmin,
			preciocosto=ppreciocosto,
			precioventa=pprecioventa,
			utilidad=putilidad,
			estado=pestado,
			idcategoria=pidcategoria
			
		WHERE idproducto = pidproducto;
	END$$

DROP PROCEDURE IF EXISTS `SP_U_Proveedor`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_Proveedor` (IN `pidproveedor` INT, IN `pnombre` VARCHAR(100), IN `prol` VARCHAR(12), IN `prut` VARCHAR(12), IN `pdireccion` VARCHAR(100), IN `ptelefono` VARCHAR(10), IN `pcelular` VARCHAR(15), IN `pemail` VARCHAR(80), IN `pcuenta1` VARCHAR(50), IN `pcuenta2` VARCHAR(50), IN `pestado` VARCHAR(30), IN `pobsv` TEXT)  BEGIN
		UPDATE proveedor SET
			nombre=pnombre,
			rol=prol,
			rut=prut,
			direccion=pdireccion,
			telefono=ptelefono,
			celular=pcelular,
			email=pemail,
			cuenta1=pcuenta1,
			cuenta2=pcuenta2,
			estado=pestado,
			obsv=pobsv
		WHERE idproveedor = pidproveedor;
	END$$

DROP PROCEDURE IF EXISTS `SP_U_TipoDocumento`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_TipoDocumento` (`pidtipodocumento` INT, `pdescripcion` VARCHAR(80))  BEGIN
		UPDATE tipodocumento SET
			descripcion=pdescripcion	
		WHERE idtipodocumento = pidtipodocumento;
	END$$

DROP PROCEDURE IF EXISTS `SP_U_TipoUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_TipoUsuario` (`pidtipousuario` INT, `pdescripcion` VARCHAR(80), `pp_venta` INT, `pp_compra` INT, `pp_producto` INT, `pp_proveedor` INT, `pp_empleado` INT, `pp_cliente` INT, `pp_categoria` INT, `pp_tipodoc` INT, `pp_tipouser` INT, `pp_anularv` INT, `pp_anularc` INT, `pp_estadoprod` INT, `pp_ventare` INT, `pp_ventade` INT, `pp_estadistica` INT, `pp_comprare` INT, `pp_comprade` INT, `pp_pass` INT, `pp_respaldar` INT, `pp_restaurar` INT, `pp_caja` INT)  BEGIN
		UPDATE tipousuario SET
			descripcion=pdescripcion,
			p_venta=pp_venta,
			p_compra=pp_compra,
			p_producto=pp_producto,
			p_proveedor=pp_proveedor,
			p_empleado=pp_empleado,
			p_cliente=pp_cliente,
			p_categoria=pp_categoria,
			p_tipodoc=pp_tipodoc,
			p_tipouser=pp_tipouser,
			p_anularv=pp_anularv,
			p_anularc=pp_anularc,
			p_estadoprod=pp_estadoprod,
			p_ventare=pp_ventare,
			p_ventade=pp_ventade,
			p_estadistica=pp_estadistica,
			p_comprare=pp_comprare,
			p_comprade=pp_comprade,
			p_pass=pp_pass,
			p_respaldar=pp_respaldar,
			p_restaurar=pp_restaurar,
			p_caja=pp_caja
		WHERE idtipousuario = pidtipousuario;
	END$$

DROP PROCEDURE IF EXISTS `SP_U_Venta`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_U_Venta` (IN `pidventa` INT, IN `pidtipodocumento` INT, IN `pidcliente` INT, IN `pidempleado` INT, IN `pserie` VARCHAR(5), IN `pnumero` VARCHAR(20), IN `pfecha` DATE, IN `ptotalventa` INT, IN `pdescuento` INT, IN `psubtotal` INT, IN `piva` INT, IN `ptotalpagar` INT, IN `pestado` VARCHAR(30))  BEGIN
		UPDATE venta SET
			idtipodocumento=pidtipodocumento,
			idcliente=pidcliente,
			idempleado=pidempleado,
			serie=pserie,
			numero=pnumero,
			fecha=pfecha,
			totalventa=ptotalventa,
			descuento=pdescuento,
			subtotal=psubtotal,
			iva=piva,
			totalpagar=ptotalpagar,
			estado=pestado
		WHERE idventa = pidventa;
	END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE `categoria` (
  `IdCategoria` int(11) NOT NULL,
  `Descripcion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`IdCategoria`, `Descripcion`) VALUES
(1, 'ABAROTES'),
(2, 'ASEO'),
(3, 'VERDURAS'),
(4, 'FERETERIA'),
(5, 'BAZAR'),
(6, 'CONGELADOS'),
(7, 'LIBRERIA'),
(8, 'BEBIDAS'),
(9, 'MASCOTAS'),
(10, 'PAQUETERIA'),
(11, 'REFRIGERADOS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `IdCliente` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Rol` varchar(12) DEFAULT NULL,
  `Rut` varchar(12) DEFAULT NULL,
  `Direccion` varchar(50) DEFAULT NULL,
  `Telefono` varchar(15) DEFAULT NULL,
  `Obsv` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

DROP TABLE IF EXISTS `compra`;
CREATE TABLE `compra` (
  `IdCompra` int(11) NOT NULL,
  `IdTipoDocumento` int(11) NOT NULL,
  `IdProveedor` int(11) NOT NULL,
  `IdEmpleado` int(11) NOT NULL,
  `Numero` varchar(20) DEFAULT NULL,
  `Fecha` date DEFAULT NULL,
  `SubTotal` int(11) DEFAULT NULL,
  `Iva` int(11) DEFAULT NULL,
  `Total` int(11) DEFAULT NULL,
  `Estado` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallecompra`
--

DROP TABLE IF EXISTS `detallecompra`;
CREATE TABLE `detallecompra` (
  `IdCompra` int(11) NOT NULL,
  `IdProducto` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `Precio` int(11) NOT NULL,
  `Total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleventa`
--

DROP TABLE IF EXISTS `detalleventa`;
CREATE TABLE `detalleventa` (
  `IdVenta` int(11) NOT NULL,
  `IdProducto` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `Costo` int(11) NOT NULL,
  `Precio` int(11) NOT NULL,
  `Total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

DROP TABLE IF EXISTS `empleado`;
CREATE TABLE `empleado` (
  `IdEmpleado` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(80) NOT NULL,
  `Sexo` varchar(1) NOT NULL,
  `FechaNac` date NOT NULL,
  `Direccion` varchar(100) DEFAULT NULL,
  `Telefono` varchar(10) DEFAULT NULL,
  `Celular` varchar(15) DEFAULT NULL,
  `Email` varchar(80) DEFAULT NULL,
  `Rut` varchar(12) DEFAULT NULL,
  `FechaIng` date NOT NULL,
  `Sueldo` int(11) DEFAULT NULL,
  `Estado` varchar(30) NOT NULL,
  `Usuario` varchar(20) DEFAULT NULL,
  `Contraseña` text,
  `IdTipoUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`IdEmpleado`, `Nombre`, `Apellido`, `Sexo`, `FechaNac`, `Direccion`, `Telefono`, `Celular`, `Email`, `Rut`, `FechaIng`, `Sueldo`, `Estado`, `Usuario`, `Contraseña`, `IdTipoUsuario`) VALUES
(1, 'Nicolas', 'Oteiza', 'M', '1990-01-11', '34 sur', NULL, '958155918', 'oteizanicolas@gmail.com', '17.494.541-1', '2021-03-15', 50000, 'ACTIVO', 'nicolaso', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 2),
(2, 'Cladio', 'Vasquez', 'M', '1969-09-10', '', '', '954126396', 'cvasquezc08@gmail.com', '11438765', '2021-03-16', 0, 'ACTIVO', 'Claudio0809', 'c34b8e99926237a38e8639762c00808c556caff5c57056f75aaaee07853ee9b8ee4447cff4b4de2094babd4f4264573c8fc6dea2976538162af852f48e5bcdad', 2),
(3, 'Pilar', 'Inzulza', 'M', '2021-03-16', '', '', '98771164', 'pil99inzl@gmail.com', '10847452', '2021-03-16', 0, 'ACTIVO', 'Pilar', '61e0c9a7ee82a11bcc341e479ac1ebd669ac52104ae92f2aadbf23692008484edf346bc5097d536e5f3ca1b6e12781c488695464d96027d7d692bfda1b87c07d', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE `producto` (
  `IdProducto` int(11) NOT NULL,
  `Codigo` varchar(50) DEFAULT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Descripcion` text,
  `Stock` int(11) DEFAULT NULL,
  `StockMin` int(11) DEFAULT NULL,
  `PrecioCosto` int(11) DEFAULT NULL,
  `PrecioVenta` int(11) DEFAULT NULL,
  `Utilidad` int(11) DEFAULT NULL,
  `Estado` varchar(30) NOT NULL,
  `IdCategoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`IdProducto`, `Codigo`, `Nombre`, `Descripcion`, `Stock`, `StockMin`, `PrecioCosto`, `PrecioVenta`, `Utilidad`, `Estado`, `IdCategoria`) VALUES
(1, '7804670490016', 'Toallitas Humedas fresh up baby ', '50 unidades 14 x 20', 4, 2, 790, 1300, 510, 'ACTIVO', 2),
(2, '7805010001527', 'Cera crema nugget 340 cc', 'tierra de color', 2, 1, 770, 1300, 530, 'ACTIVO', 2),
(3, '2804600280071', 'Cera pintura para piso VARELA 1 litro', '', 5, 2, 790, 1400, 610, 'ACTIVO', 2),
(4, '7805010001510', 'CERA CREMA NUGGET340 CC', 'COLOR CAOBA', 2, 1, 770, 1300, 530, 'ACTIVO', 2),
(5, '7805010001534', 'CERA CREMA NUGGET 340 CC', 'COLOR AMARILLA', 2, 1, 770, 1300, 530, 'ACTIVO', 2),
(6, '7805010001558', 'CERA CREMA NUGGET 340 CC', 'COLOR INCOLORA', 2, 1, 770, 1300, 530, 'ACTIVO', 2),
(7, '7805027000162', 'CERA ARUBA 500 CC', 'AROMATIZADA ', 3, 2, 890, 1300, 410, 'ACTIVO', 2),
(8, '7805027000049', 'CERA ARUBA 300 CC', '', 3, 2, 511, 750, 239, 'ACTIVO', 2),
(9, '7805027000537', 'CERA ARUBA 300 CC', 'AMARILLO', 3, 2, 511, 750, 239, 'ACTIVO', 2),
(10, '1001', 'MOTE 1/2 K', '', 5, 2, 500, 650, 150, 'ACTIVO', 3),
(11, '742832308830', 'SERVILLETAS REGIO 300 UNIDADES', '', 3, 1, 982, 1500, 518, 'ACTIVO', 2),
(12, '74\'83\'308\'86', 'SERVILLETAS REGGIO 200 UNIDADES', '', 4, 1, 650, 1200, 550, 'ACTIVO', 2),
(13, '7812050000687', 'DESENGRASANTE ACTIVE 1 LITRO', '', 3, 1, 1000, 1700, 700, 'ACTIVO', 2),
(14, '8010690093147', 'ANTIGRASA NARANJA GRINS 500 ML', 'GATILLO', 2, 1, 1200, 1800, 600, 'ACTIVO', 2),
(15, '7805000316068', 'LAVALOZAS QUIX  PUREZA ESCENCIAL 500ML', 'QUIX', 3, 2, 1000, 1590, 590, 'ACTIVO', 2),
(16, '7806500241294', 'SERVILLETA ABOLENGO 40 UNIDADES', '', 19, 5, 120, 240, 120, 'ACTIVO', 2),
(17, '7804663190152', 'SERVILLETA OPTIMO 50 UNIDADES', '', 20, 5, 120, 240, 120, 'ACTIVO', 2),
(18, '7805020000794', 'LIMPIADOR EN CREMA  750 GRS', 'AROMA LIMON', 3, 1, 1100, 1500, 400, 'ACTIVO', 2),
(19, '7805000115913', 'LAVALOSA QUIX  750 ML', '', 2, 1, 1000, 1900, 900, 'ACTIVO', 2),
(20, '7812122000447', 'LAVALOZAS ACTIVE  2 LITROS', '', 3, 1, 1000, 1800, 800, 'ACTIVO', 2),
(21, '7804660170126', 'LAVALOZAS KLIN 500 ML', '', 3, 2, 750, 1200, 450, 'ACTIVO', 2),
(22, '8010690023908', 'LIMPIAVIDRIOS MULTIUSO  GRINS 500 ML', '', 2, 1, 1050, 1800, 750, 'ACTIVO', 2),
(23, '7805080100014', 'CLORO CLORINDA 250 GR', 'CLORINDA', 10, 3, 350, 550, 200, 'ACTIVO', 2),
(24, '7805025690938', 'POETT  250 ML', '', 4, 2, 438, 630, 192, 'ACTIVO', 2),
(25, '7805025690419', 'CLORO ROPA BLANCA 370 GRS', '', 5, 3, 550, 790, 240, 'ACTIVO', 2),
(26, '7805080100021', 'CLORO CLORINDA 1 KILO', '', 4, 2, 890, 1200, 310, 'ACTIVO', 2),
(27, '7805020311012', 'LIMPIAPISOS EXCELL 900 CC', '', 2, 1, 750, 1150, 400, 'ACTIVO', 2),
(28, '7812122700958', 'LIMPIADOR MULTIUSO 2 LITROS PRIMAVERA', '', 2, 1, 1000, 1700, 700, 'ACTIVO', 2),
(29, '7805040004499', 'DESODORANTE AMBIENTAL AROM', 'CHIRIMOYA ALEGRE', 2, 1, 1100, 1600, 500, 'ACTIVO', 2),
(30, '7793253002152', 'DESODORANTE AMBIENTAL POETT 360 ML', '', 2, 1, 1100, 1600, 500, 'ACTIVO', 2),
(31, '7790520014184', 'DESINFECTANTE LISOFORM', '', 2, 1, 2700, 3200, 500, 'ACTIVO', 2),
(32, '7805300050037', 'DESODORANTE AMBIENTAL FRES KIT', '', 4, 2, 889, 1500, 611, 'ACTIVO', 2),
(33, '8699009446944', 'DESINFECTANTE DOÑA GABY', '', 4, 2, 1700, 2400, 700, 'ACTIVO', 2),
(34, '2001', 'PAÑOS AMARILLOS PARA SACUDIR', '', 8, 3, 300, 490, 190, 'ACTIVO', 2),
(35, '2002', 'PAÑOS ROSADORS CON OJAL PARA LIMPIAR PISOS', '', 2, 1, 500, 850, 350, 'ACTIVO', 2),
(36, '2003', 'PAÑO BLANCO CON OJAL PARA PISOS', '', 3, 2, 500, 850, 350, 'ACTIVO', 2),
(37, '2004', 'PAÑO DANZARINA', '', 3, 1, 250, 490, 240, 'ACTIVO', 2),
(38, '7806800008733', 'BOLSA PARA BASURA PEQUEÑA MANLAC', '50 X 70', 5, 2, 500, 700, 200, 'ACTIVO', 2),
(39, '7804673690017', 'BOLSA PARA BASURA 50 X 70 GLITT', '', 5, 2, 390, 700, 310, 'ACTIVO', 2),
(40, '7806800004018', 'VIRUTILLA GRUESA PARA OLLAS', 'MANLAC', 10, 5, 110, 200, 90, 'ACTIVO', 2),
(41, '7806800035036', 'GUANTE MULTIUSO', '', 4, 1, 832, 1300, 468, 'ACTIVO', 2),
(42, '7804670690003', 'BOLSA PARA BASURA 50 X 70  20 UNIDADES', '', 4, 2, 650, 1300, 650, 'ACTIVO', 2),
(43, '63331064805', 'BOLSA PARA BASURA REGIO  70 X 90 10 UNIDADES', '', 5, 3, 550, 1200, 650, 'ACTIVO', 2),
(44, '7804655190573', 'SUAVISANTE PARA ROPA 500 ML', 'DOÑA GABY', 4, 2, 690, 990, 300, 'ACTIVO', 2),
(45, '945127004', 'SUAVISANTE PARA ROPA  2 LITROS', 'MASTER', 2, 1, 910, 1800, 890, 'ACTIVO', 2),
(46, '7812288973333', 'SUAVISANTE PARA ROPA 2 LITROS', 'ACTIVE', 2, 1, 1000, 1800, 800, 'ACTIVO', 2),
(47, '7804920006264', 'SUAVISANTE PARA ROPA PIEL DELICADA  1 LITRO', 'FUZOL     BODEGUITA', 3, 1, 1000, 1390, 390, 'ACTIVO', 2),
(48, '7804663500173', 'DETERGENTE PARA ROPA 1 KILO', 'MOPIC', 12, 5, 879, 1500, 621, 'ACTIVO', 2),
(49, '7500435146364', 'DETERGENTE PARA ROPA MANZANILLA 400 GRS', 'ACE', 5, 2, 690, 990, 300, 'ACTIVO', 2),
(50, '7804609730268', 'DETERGENTE PARA ROPA MATIC 800 GRS', '', 6, 3, 800, 1200, 400, 'ACTIVO', 2),
(51, '7805000313616', 'DETERGENTE PARA ROPA  400 GRS', 'OMO', 5, 3, 790, 110, -680, 'ACTIVO', 2),
(52, '7804663190008', 'TOALLA DE PAPEL 100 METRO', 'OPTIMO', 3, 2, 1390, 2200, 810, 'ACTIVO', 2),
(53, '7806500404620', 'TOALLA DE PAPEL  ECONOMICA ', 'ABOLENGO', 10, 3, 290, 420, 130, 'ACTIVO', 2),
(54, '74\'83\'1\'2363', 'TOALLA DE PAPEL 12 METROS  3 ROLLOS', 'BIO FRESH', 9, 2, 821, 1200, 379, 'ACTIVO', 2),
(55, '7804670690270', 'TOALLA DE PAPEL 3 ROLLOS', 'REGIO', 1, 1, 543, 1200, 657, 'ACTIVO', 2),
(56, '7804612610274', 'PAPEL HIGIENICO 30 METROS', 'FLORAX', 28, 14, 660, 1400, 740, 'ACTIVO', 2),
(57, '7898920700765', 'PAPEL HIGIENICO 30 METROS', 'TOM', 7, 3, 772, 1500, 728, 'ACTIVO', 2),
(58, '700083653611', 'VIRUTILLA GRUESA PARA OLLAS', 'BRIGH', 12, 4, 120, 300, 180, 'ACTIVO', 2),
(59, '780556984756', 'DETERGENTE LIQUIDO PARA ROPA 5 LITROS', 'DIAL', 3, 2, 1700, 2400, 700, 'ACTIVO', 2),
(60, '7801265211407', 'DESENGRASANTE LIQUIDO 5 LITROS', 'ACTIVE', 4, 2, 2500, 4500, 2000, 'ACTIVO', 2),
(61, '780556984756', 'DETERGENTE LIQUIDO AZUL PARA ROPA 5 LITROS', 'DIAL', 3, 2, 1000, 1700, 700, 'ACTIVO', 2),
(62, '945127003', 'SUAVISANTE PARA ROPA 5 LITROS', 'MASTER', 2, 1, 2100, 3900, 1800, 'ACTIVO', 2),
(63, '2005', 'CLORO  5 LITROS', 'MAITAKI', 3, 2, 1000, 1800, 800, 'ACTIVO', 2),
(64, '7805836104402', 'SOPAPO PARA BAÑO', 'ACUENTA', 2, 1, 2350, 2800, 450, 'ACTIVO', 2),
(65, '7804625040181', 'PALA PARA BASURA', 'ACUENTA', 3, 2, 1790, 2200, 410, 'ACTIVO', 2),
(66, '7805007139738', 'VELA  4 UNIDADES LARGA', 'ANTORCHA', 6, 4, 306, 650, 344, 'ACTIVO', 2),
(67, '7804655191419', 'JABON DE LAVAR  200 GRS', 'DOÑA GABY', 6, 2, 650, 950, 300, 'ACTIVO', 2),
(68, '7807900000894', 'JABON DE LAVAR 170 GRS', 'ACUENTA', 3, 2, 550, 840, 290, 'ACTIVO', 2),
(69, '2006', 'JABON DE LAVAR ', 'POPEYE', 4, 2, 776, 1100, 324, 'ACTIVO', 2),
(70, '2007', 'ESPONJA AMARILLA PARA LAVAR LOZA', '', 10, 5, 100, 250, 150, 'ACTIVO', 2),
(71, '7806800004018', 'ESPONJA GRUESA PARA OLLAS', 'MANLAC', 10, 5, 110, 200, 90, 'ACTIVO', 2),
(72, '7806800003011', 'VIRUTILLA FINA PARA OLLAS  6 UNIDADES', 'MANLAC', 5, 3, 298, 750, 452, 'ACTIVO', 2),
(73, '6922961996502', 'PASTA DE DIENTES 90 GRS', 'FRESH UP', 12, 5, 1133, 1550, 417, 'ACTIVO', 2),
(74, '7804632020008', 'ESPONJA PARA CALZADO', 'LIDER', 2, 1, 1090, 1400, 310, 'ACTIVO', 2),
(75, '6922961998827', 'PASTA DE DIENTES  50 GRS', 'FRESH UP', 12, 6, 333, 750, 417, 'ACTIVO', 2),
(76, '7805040910042', 'PASTA DE ZAPATOS CAFE', 'LIDER', 2, 1, 990, 1300, 310, 'ACTIVO', 2),
(77, '7805040001658', 'BETUN LIQUIDO PARA ZAPATOS', 'VIRGINIA', 3, 1, 1690, 2100, 410, 'ACTIVO', 2),
(78, '7804670490030', 'ENJUAGUE BUCAL 250 ML', 'FRESH UP', 2, 1, 990, 1590, 600, 'ACTIVO', 2),
(79, '7805040910035', 'PASTA PARA ZAPATOS NEGRA', 'LIDER', 2, 1, 990, 1300, 310, 'ACTIVO', 2),
(80, '945127014', 'RENOVADOR DE GOMAS GATILLO 500 ML', 'MASTER', 2, 1, 1100, 1990, 890, 'ACTIVO', 2),
(81, '7500435128599', 'SHAMPOO MICELAR 200 ML', 'PANTENE', 3, 2, 840, 1700, 860, 'ACTIVO', 1),
(82, '7500435138017', 'SHAMPOO 180 ML', 'HEAD SHOULDERS', 3, 2, 840, 1700, 860, 'ACTIVO', 1),
(83, '7804920005816', 'SHAMPOO MICELAR  750 ML', 'BALLERINA', 1, 1, 1349, 1990, 641, 'ACTIVO', 2),
(84, '7804920005656', 'ACONDICIONADOR DETOX', 'BALLERINA', 2, 1, 1349, 1990, 641, 'ACTIVO', 2),
(85, '7804920002624', 'SHAMPOO BRILLO LUMINOSO 750ML', 'BALLERINA', 1, 1, 1349, 1990, 641, 'ACTIVO', 2),
(86, '7804920018779', 'SHAMPOO BRILLO LUMINOSO  900 ML', 'BALLERINA', 2, 1, 990, 1400, 410, 'ACTIVO', 2),
(87, '7804920055040', 'ACONDICIONADOR  BRILLO LUMINOSO', 'BALLERINA', 1, 1, 990, 1400, 410, 'ACTIVO', 2),
(88, '7804920006905', 'SHAMPOO LARGO INCREIBLE 900 ML', 'BALLERINA', 1, 1, 990, 1400, 410, 'ACTIVO', 2),
(89, '7804945011045', 'JABON DE GLICERINA CREMOSO', 'SIMONDS', 1, 1, 800, 1450, 650, 'ACTIVO', 1),
(90, '7804920003225', 'SHAMPOO HIDRATACION Y SUAVIDAD SIN SAL 900 ML', 'BALLERINA', 1, 1, 900, 1450, 550, 'ACTIVO', 1),
(91, '7804920018779', 'SHAMPOO BRILLO LUMINOSO 900', 'BALLERINA', 1, 1, 950, 1400, 450, 'ACTIVO', 1),
(92, '7804920350176', 'JABON DE TOCADOR DOBLE HUMECTACION', 'BALLERINA', 1, 1, 800, 1450, 650, 'ACTIVO', 1),
(93, '7804920350664', 'JABON LIQUIDO HIPOALERGENICO', 'BALLERINA', 1, 1, 800, 1450, 650, 'ACTIVO', 1),
(94, '7501026006661', 'JABON DE TOCADOR EN BARRA 100 GRS', 'ROSA VENUS', 6, 2, 333, 650, 317, 'ACTIVO', 1),
(95, '8999999527679', 'JABON DE TOCADOR EN BARRA 80 GRS', 'LUX', 4, 2, 454, 690, 236, 'ACTIVO', 1),
(96, '7804920008008', 'ACONDICIONADOR DE MANZANILLA', 'SUAVELINA', 3, 2, 808, 1400, 592, 'ACTIVO', 1),
(97, '7591066711014', 'MAQUINA DE AFEITAR X TREME 3  ROSADA', 'SCHICK', 12, 4, 451, 700, 249, 'ACTIVO', 1),
(98, '7591066721020', 'MAQUINA DE AFEITAR HOMBRE X TREME 3', 'SCHICK', 12, 4, 451, 700, 249, 'ACTIVO', 1),
(99, '7793620003249', 'TOALLA HIGIENICA NORMAL CON ALAS 8 UNIDADES', 'KOTEX', 3, 2, 850, 1200, 350, 'ACTIVO', 1),
(100, '7794626996740', 'TOALLA HIGIENICA NORMAL CON ALAS 8 UNIDADES', 'MIMOSA', 3, 2, 490, 850, 360, 'ACTIVO', 1),
(101, '7790250096061', 'TOALLA HIGIENICA ULTRADELGADA 8 UNIDADES', 'LADYSOFT', 3, 2, 670, 950, 280, 'ACTIVO', 1),
(102, '7809604028464', 'PROTECTORES DIARIOS  40 UNIDADES', 'KOTEX', 3, 2, 850, 1200, 350, 'ACTIVO', 1),
(103, '7806500962335', 'PROTECTORES ULTRADELGADOS 20 UNIDADES', 'LADYSOFT', 3, 2, 450, 800, 350, 'ACTIVO', 1),
(104, '7702425808058', 'TAMPONES DIGITALES 12 UNIDADES', 'KOTEX', 3, 2, 850, 1200, 350, 'ACTIVO', 1),
(105, '7806500966012', 'TAMPONES  REGULAR MEDIO 8 UNIDADES', 'LADYSOFT', 2, 1, 1490, 2100, 610, 'ACTIVO', 1),
(106, '7804670490016', 'TOALLAS HUMEDAS', 'FRESH UP', 4, 2, 790, 1300, 510, 'ACTIVO', 1),
(107, '7804653341021', 'PAPEL HIGIENICO 6 ROLLOS  22 METROS', 'LIDER', 6, 3, 1000, 1800, 800, 'ACTIVO', 1),
(108, '6953103242126', 'ATRAPA  INSECTOS', '', 3, 1, 500, 1200, 700, 'ACTIVO', 1),
(109, '7794626004001', 'PAÑAL RECIEN NACIDO  20 UNIDADES', 'HUGGIES', 1, 1, 2800, 4200, 1400, 'ACTIVO', 1),
(110, '7809604030238', 'PAÑAL G 20 UNIDADES', 'HUGGIES', 1, 1, 2589, 3700, 1111, 'ACTIVO', 1),
(111, '2008', 'MATAMOSCAS', '', 2, 1, 350, 500, 150, 'ACTIVO', 1),
(112, '2009', 'ESCOBILLA PARA LIMPIAR WC', '', 2, 1, 890, 1900, 1010, 'ACTIVO', 1),
(113, '7806550002470', 'APOSITOS PLENITUD 20 UNIDADES', 'PLENITUD', 1, 1, 2600, 3900, 1300, 'ACTIVO', 1),
(114, '7806500773429', 'PAÑAL PARA ADULTOS 8 UNIDADES', 'COTIDIAN', 2, 1, 3100, 4100, 1000, 'ACTIVO', 1),
(115, '7805525000558', 'CLORO COLOR QUITAMANCHAS  1 LITRO', 'LIDER', 11, 3, 1450, 2090, 640, 'ACTIVO', 2),
(116, '7805525000527', 'CLORO QUITAMANCHAS BLANCAS RADIANTE 1 LITRO', 'LIDER', 3, 2, 780, 1390, 610, 'ACTIVO', 2),
(117, '7805025693359', 'CLOROX  ROPA BLANCA  1,900 GRS', 'CLOROX', 5, 3, 1630, 2390, 760, 'ACTIVO', 2),
(118, '7804930014600', 'CERA LIQUIDA AUTOBRILLO 900 ML INCOLORA', 'LIDER', 2, 1, 1980, 2090, 110, 'ACTIVO', 2),
(119, '7805040001085', 'CERA LIQUIDA  AUTOBRILLO ROJA 90 ML', 'VIRGINIA', 2, 1, 1690, 2690, 1000, 'ACTIVO', 2),
(120, '3001', 'MACETERO CAFE CUADRADO MEDIANO', '', 1, 1, 800, 1300, 500, 'ACTIVO', 5),
(121, '3002', 'MACETERO RECTANGULAR VERDE GRANDE', '', 1, 1, 1200, 1800, 600, 'ACTIVO', 5),
(122, '3003', 'MACETERO REDONDO MEDIANO CAFE', '', 4, 2, 700, 1200, 500, 'ACTIVO', 5),
(123, '7793125059857', 'FORRO DE CORTINA DE BAÑO', '', 3, 1, 1200, 1700, 500, 'ACTIVO', 5),
(124, '3004', 'MACETERO REDONDO GRANDE COLOR CAFE', '', 3, 1, 0, 2300, 2300, 'ACTIVO', 4),
(125, '3005', 'MACETERO REDONDO PEQUEÑO COLOR CAFE', '', 4, 2, 350, 700, 350, 'ACTIVO', 4),
(126, '3006', 'MACETERO MEDIANO REDONDO COLOR CAFE', '', 5, 2, 450, 900, 450, 'ACTIVO', 5),
(127, '4001', 'BOLSA CAMISETA BLANCA  1 KILO', '', 6, 3, 650, 1100, 450, 'ACTIVO', 5),
(128, '4002', 'BOLSA CAMISETA BLANCA 2 KILOS', '', 10, 2, 860, 1900, 1040, 'ACTIVO', 5),
(129, '4003', 'BOLSA COLACION ', '', 10, 3, 500, 850, 350, 'ACTIVO', 5),
(130, '4004', 'CAJA RECTANGULAR CON TAPA TRANSPARENTE', '', 3, 1, 1200, 2000, 800, 'ACTIVO', 5),
(131, '4005', 'FUENTE REDONDA GRANDE', '', 3, 1, 1200, 1700, 500, 'ACTIVO', 5),
(132, '4010', 'JUEGO DE NAIPE INGLES', '', 2, 1, 1000, 1500, 500, 'ACTIVO', 5),
(133, '7793125059857', 'FORRO DE CORTINA DE BAÑO', '', 3, 1, 1200, 1700, 500, 'ACTIVO', 5),
(134, '4011', 'BOMBONERO DE VIDRIO GRANDE', '', 2, 1, 1000, 1500, 500, 'ACTIVO', 5),
(135, '4012', 'JARRO CON TAPA 1/2 LITRO', '', 3, 1, 1000, 1900, 900, 'ACTIVO', 5),
(136, '4013', 'JARRO VIDRIO 1/2 LITRO', '', 3, 2, 1000, 1700, 700, 'ACTIVO', 5),
(137, '4014', 'BOMBONERO VIDRIO MEDIANO', '', 2, 1, 1000, 1500, 500, 'ACTIVO', 5),
(138, '4015', 'BOMBONERO VIDRIO PEQUEÑO', '', 2, 1, 1000, 1500, 500, 'ACTIVO', 5),
(139, '7805000115906', 'DETERGENTE PARA LOZAS 500 ML', 'QUIX', 4, 2, 1000, 1590, 590, 'ACTIVO', 5),
(140, '7805040003539', 'LIMPIADOR DE PISOS ARONA VAINILLA 900 ML', 'VIRGINIA', 3, 2, 650, 1200, 550, 'ACTIVO', 1),
(141, '7805300049161', 'INSECTICIDA AEROSOL  CASA Y JARDIN 220 CC', 'TANAX', 3, 2, 1690, 2390, 700, 'ACTIVO', 1),
(142, '7805020002385', 'PASTILLAS PARA ESTANQUE DE BAÑO', 'EXCELL', 1, 1, 1500, 1900, 400, 'ACTIVO', 1),
(143, '7804663500234', 'LIQUIDO PARA LIMPIAR PISOS 5 LITROS', 'PQ', 2, 1, 1500, 2200, 700, 'ACTIVO', 1),
(144, '7804663500050', 'DETERGENTE LIQUIDO PARA ROPA  5 LITROS', 'MATIC PQ', 5, 2, 1000, 1700, 700, 'ACTIVO', 1),
(145, '7804660170522', 'LIMPIADOR EN POLVO  CON CLORO TIPO KLENZO', 'KIL FRESH', 2, 1, 620, 1190, 570, 'ACTIVO', 2),
(146, '7804660170515', 'LIMPIADOR EN POLVO TIPO KLENZO  FLORAL', 'KLIN FRESH', 2, 1, 620, 1190, 570, 'ACTIVO', 2),
(147, '7804655190481', 'DETERGENTE MATIC   EN POLVO 400 GRS', 'DOÑA GABY', 4, 1, 660, 1090, 430, 'ACTIVO', 2),
(148, '7801255000318', 'CHORITOS EN ACEITE ', 'COLORADO', 3, 1, 890, 1390, 500, 'ACTIVO', 1),
(149, '7804609730343', 'DETERGENTE BIO FRESCURA 400 GRS.', 'BIO FRESCURA', 6, 2, 570, 990, 420, 'ACTIVO', 2),
(150, '7751851024136', 'DETERGENTE EN POLVO PARA ROPA DE BEBE', 'SAPOLIO', 2, 1, 720, 1350, 630, 'ACTIVO', 2),
(151, '7590011205158', 'GALLETAS  CLUB SOCIAL  ORIGINAL', 'CLUB SOCIAL', 18, 5, 110, 150, 40, 'ACTIVO', 1),
(152, '7622300258061', 'GALLETA CLUB SOCIAL QUESO', 'CLUB SOCIAL', 9, 4, 110, 150, 40, 'ACTIVO', 1),
(153, '4016', 'CEPILLO DE PELO', '', 4, 2, 1350, 1990, 640, 'ACTIVO', 5),
(154, '7804000001219', 'MERMELADA DE DAMASCO LIGHT', 'TIA LIA', 1, 1, 590, 990, 400, 'ACTIVO', 1),
(155, '804000001233', 'MERMELADA DE MORA LIGHT', 'TIA LIA', 1, 1, 590, 990, 400, 'ACTIVO', 1),
(156, '7804000001240', 'MERMELADA DE MORA LIGHT', 'TIA LIA', 1, 1, 590, 990, 400, 'ACTIVO', 1),
(157, '7802810005632', 'MERMELADA DE FRUTILLA', 'WATTS', 1, 1, 790, 1190, 400, 'ACTIVO', 1),
(158, '7802810031020', 'MERMELADA DE DURAZNO', 'WATTS', 1, 1, 790, 1190, 400, 'ACTIVO', 1),
(159, '7802810031013', 'MERMELADA DE DAMASCO', 'WATTS', 2, 1, 790, 1190, 400, 'ACTIVO', 1),
(160, '7804907756502', 'CREMA HIDRATANTE PARA MANOS', 'PIELARMINA', 4, 1, 600, 990, 390, 'ACTIVO', 5),
(161, '8999999527693', 'JABON BARRA', 'LUX', 4, 2, 350, 600, 250, 'ACTIVO', 2),
(162, '7702993020890', 'COYAK FRESA 24 UNIDADES', 'LOKIÑO', 24, 5, 50, 100, 50, 'ACTIVO', 2),
(163, '7801552001216', 'GALLETA SODA INDIVIDUAL', 'TRENDY', 5, 2, 140, 250, 110, 'ACTIVO', 1),
(164, '7804920003966', 'JABON LIQUIDO GRANADA 1 LITRO', 'BALLERINA', 2, 1, 990, 1490, 500, 'ACTIVO', 2),
(165, '7802215504679', 'GALLETAS NICK DE LIMON', 'NIK', 5, 2, 250, 390, 140, 'ACTIVO', 1),
(166, '7802215504662', 'GALLETA OBLEA FRUTILLA', 'NIK', 4, 2, 250, 390, 140, 'ACTIVO', 1),
(167, '7802215504655', 'GALLETA OBLEA BOCADO', 'NIK', 6, 2, 250, 390, 140, 'ACTIVO', 1),
(168, '7802000014703', 'PAPAS FRITAS CORTE AMERICANO 380 GRS', 'LAYS', 3, 1, 1650, 2090, 440, 'ACTIVO', 1),
(169, '7802000011108', 'PAPAS FRITAS CORTE LISO 230 GRS', 'LAYS', 3, 1, 1300, 1790, 490, 'ACTIVO', 1),
(170, '7613035953741', 'SEMOLA CON LECHE', 'NESTLE				', 3, 1, 500, 650, 150, 'ACTIVO', 1),
(171, '7802200132696', 'MENTITAS  DULCES', 'AMBROSOLI', 24, 5, 110, 250, 140, 'ACTIVO', 1),
(172, '7702250120202', 'ESCOBILLA DE LAVAR', 'DOÑA GABY\n', 3, 1, 1100, 1490, 390, 'ACTIVO', 1),
(173, '7802920009469', 'QUESO CHACRA ', 'RIO BUENO', 5, 2, 1450, 1790, 340, 'ACTIVO', 1),
(174, '7804947003888', 'INSECTICIDA AEROSOL', 'HOME SWEET', 2, 1, 1550, 1980, 430, 'ACTIVO', 2),
(175, '7804907957725', 'PIELARMINA CLINICAL  ALCOHOL GEL', 'PIELARMINA', 2, 1, 950, 1300, 350, 'ACTIVO', 5),
(176, '7613032517748', 'LECHE NIDO  EN POLVO BUEN DIA 130 GRS', 'NESTLE', 3, 1, 730, 990, 260, 'ACTIVO', 1),
(177, '7802920007120', 'LECHE CON CHOCOLATE 1 LITRO', 'COLUN', 3, 1, 990, 1350, 360, 'ACTIVO', 1),
(178, '801930010397', 'PRIETA PREMIUM', 'RECETA DEL ABUELO', 3, 1, 1000, 1450, 450, 'ACTIVO', 1),
(179, '7802906030340', 'QUESO GAUDA  LAMINADO 1 KILO', 'CHILOLAC', 1, 1, 5400, 6900, 1500, 'ACTIVO', 1),
(180, '7801323000318', 'SALMON PORCIONADO 500 GRS', 'EL GOLFO', 2, 1, 4890, 5990, 1100, 'ACTIVO', 6),
(181, '7708087000014', 'SURTIDO DE MARISCOS 400 GRS', 'DEL PEDREGAL', 4, 1, 1000, 1590, 590, 'ACTIVO', 6),
(182, '7801323001155', 'SURTIDO DE MARISCOS 250 GRS', 'EL GOLFO', 2, 1, 1100, 1490, 390, 'ACTIVO', 6),
(183, '7801930008219', 'PATE DE CAMPO', 'RECETA DEL ABUELO', 2, 1, 400, 550, 150, 'ACTIVO', 1),
(184, '7809611700261', 'CROQUETA DE POLLO', 'SUPER POLLO', 6, 2, 250, 390, 140, 'ACTIVO', 1),
(185, '7801907009782', 'CARNE MOLINA', 'SAN JORGE', 3, 1, 940, 1290, 350, 'ACTIVO', 6),
(186, '7801930015224', 'HAMBURGUESA DE CARNE 50 GRS', 'LA ESPAÑOLA', 10, 3, 130, 220, 90, 'ACTIVO', 6),
(187, '739907000010', 'HARINA DE MAIZ BLANCO', 'P.A.N', 5, 2, 1400, 1950, 550, 'ACTIVO', 1),
(188, '7802612000057', 'HARINA CRUDA SIN POLVOS DE HORNEAR', 'MOLINO YANINE', 1, 1, 590, 790, 200, 'ACTIVO', 1),
(189, '7798031153705', 'SPAGHETTI 400GR', 'COLISEO', 10, 3, 620, 850, 230, 'ACTIVO', 1),
(190, '7804608222467', 'CABELLO DE ANGEL 400GR', 'COLISEO', 4, 3, 620, 850, 230, 'ACTIVO', 1),
(191, '7804608223013', 'CODOS 400GR', 'COLISEO', 4, 3, 620, 850, 230, 'ACTIVO', 1),
(192, '7798031153736', 'ESPIRALES 400G', 'COLISEO', 4, 3, 620, 850, 230, 'ACTIVO', 1),
(193, '7802612000040', 'HARINA CON POLVOS DE HORNEAR', 'MOLINO YANINE', 12, 3, 500, 790, 290, 'ACTIVO', 1),
(194, '7809559200342', 'HARINA CON POLVOS DE HORNEAR', 'ACUENTA', 20, 2, 484, 690, 206, 'ACTIVO', 1),
(195, '7802612000286', 'HARINA INTEGRAL', 'MOLINO YANINE', 3, 3, 590, 850, 260, 'ACTIVO', 1),
(196, '7802575002037', 'CANUTONES 400GR', 'CAROZZI', 2, 2, 640, 900, 260, 'ACTIVO', 1),
(197, '7802575002235', 'ESPIRALES 400GR', 'CAROZZI', 3, 3, 640, 900, 260, 'ACTIVO', 1),
(198, '7803111002757', 'CORBATAS 400GR', 'ACUENTA', 3, 3, 300, 450, 150, 'ACTIVO', 1),
(199, '400006837677', 'SPAGUETTI N5', 'ACUENTA', 6, 3, 300, 450, 150, 'ACTIVO', 1),
(200, '7807900004830', 'FETUCCINE', 'SELECCION', 5, 3, 690, 950, 260, 'ACTIVO', 1),
(201, '7804608221798', 'CODOS 400GR', 'SELECCION', 3, 2, 690, 950, 260, 'ACTIVO', 1),
(202, '7802575004437', 'SPAGUETTI N5', 'CAROZZI', 3, 3, 640, 900, 260, 'ACTIVO', 1),
(203, '7802575004635', 'TALLARINES 87', 'CAROZZI', 5, 3, 640, 900, 260, 'ACTIVO', 1),
(204, '7802575006455', 'MARIPOSAS 250GR', 'CAROZZI', 2, 2, 640, 900, 260, 'ACTIVO', 1),
(205, '7802575003249', 'ALFABETO 250GR', 'CAROZZI', 3, 2, 640, 900, 260, 'ACTIVO', 1),
(206, '7802575006233', 'CORBATITAS 400GR', 'CAROZZI', 3, 3, 640, 900, 260, 'ACTIVO', 1),
(207, '7794870055767', 'ACEITE 900ML', 'ACUENTA', 9, 3, 879, 1250, 371, 'ACTIVO', 1),
(208, '7802810012029', 'ACEITE VEGETAL 1L', 'BELMONT', 3, 3, 1400, 1890, 490, 'ACTIVO', 1),
(209, '400006753687', 'ACEITE MARAVILLA 1L', 'LIDER', 3, 3, 1390, 1750, 360, 'ACTIVO', 1),
(210, '7802810012531', 'ACEITE VEGETAL 250ML', 'BELMONT', 7, 3, 520, 730, 210, 'ACTIVO', 1),
(211, '7802351534707', 'JUGO DE LIMON 500ML', 'DON JUAN', 8, 3, 375, 500, 125, 'ACTIVO', 1),
(212, '7802345000607', 'VINAGRE BLANCO 250ML', 'MONTANER', 6, 3, 450, 650, 200, 'ACTIVO', 1),
(213, '7804610401553', 'ACEITE DE OLIVA EXTRA VIRGEN 250ML', 'SANTÉ', 2, 2, 1690, 2590, 900, 'ACTIVO', 1),
(214, '7802000014925', 'AVENA INTEGRAL INSTANTANEA 750G', 'QUAKER', 1, 1, 1551, 2200, 649, 'ACTIVO', 1),
(215, '7802000014949', 'AVENA INTEGRAL INSTANTANEA 450G', 'QUAKER', 2, 1, 931, 1490, 559, 'ACTIVO', 1),
(216, '7802000014956', 'AVENA INTEGRAL TRADICIONAL 450G', 'QUAKER', 2, 1, 931, 1490, 559, 'ACTIVO', 1),
(217, '7613287193896', 'MILO 230G', 'NESTLÉ', 2, 1, 990, 1590, 600, 'ACTIVO', 1),
(218, '7613287193834', 'CEREAL TRIX 220G', 'NESTLÉ', 2, 1, 990, 1590, 600, 'ACTIVO', 1),
(219, '7802000014932', 'AVENA INTEGRAL TRADICIONAL 750G', 'QUAKER', 1, 1, 1551, 2200, 649, 'ACTIVO', 1),
(220, '7804651630103', 'SAL PARRILLERA 750G', 'LIDER', 3, 2, 940, 1390, 450, 'ACTIVO', 1),
(221, '7804651630097', 'SAL FINA', 'ACUENTA', 4, 2, 200, 350, 150, 'ACTIVO', 1),
(222, '7803600041236', 'SAL COCINA ', 'LOBOS', 4, 2, 340, 500, 160, 'ACTIVO', 1),
(223, '7803010610022', 'LEVADURA SECA', 'COLLICO', 20, 5, 149, 250, 101, 'ACTIVO', 1),
(224, '7802575007438', 'SÉMOLA', 'CAROZZI', 3, 2, 550, 790, 240, 'ACTIVO', 1),
(225, '7802640793563', 'ALMIDÓN DE MAIZ 100G', 'MAIZENA', 2, 1, 1490, 1750, 260, 'ACTIVO', 1),
(226, '78098152', 'SAL DE SELECCION', 'LOBOS', 3, 2, 350, 550, 200, 'ACTIVO', 1),
(227, '7804627780047', 'AZUCAR GRANULADA', 'ACUENTA', 4, 2, 550, 750, 200, 'ACTIVO', 1),
(228, '7801505000549', 'AZUCAR BLANCA GRANULADA', 'MERKAT', 3, 2, 679, 990, 311, 'ACTIVO', 1),
(229, '7801505231912', 'AZUCAR BLANCA GRANULADA', 'IANZA', 3, 3, 770, 1100, 330, 'ACTIVO', 1),
(230, '7801505231974', 'AZUCAR FLOR 500G', 'IANZA', 3, 2, 750, 1100, 350, 'ACTIVO', 1),
(231, '7804918401811', 'ENDULZANTE SUCRALOSA', 'NATURA LIST', 2, 2, 1200, 1500, 300, 'ACTIVO', 1),
(232, '7804918401835', 'ENDULZANTE STEVIA', 'NATURA LIST', 2, 2, 1700, 2000, 300, 'ACTIVO', 1),
(233, '7801420220145', 'ARROZ GRADO 2 500G', 'TUCAPEL', 3, 2, 550, 800, 250, 'ACTIVO', 1),
(234, '7804608220098', 'ARROZ GRADO 2', 'COLISEO', 3, 660, 0, 1100, 1100, 'ACTIVO', 1),
(235, '7801420000136', 'ARROZ GRADO 1', 'LIDER', 3, 2, 1000, 1300, 300, 'ACTIVO', 1),
(236, '7801420000808', 'ARROZ INTEGRAL GRADO 1', 'LIDER', 3, 2, 1100, 1450, 350, 'ACTIVO', 1),
(237, '7804608221347', 'ARROZ GRADO 2', 'ACUENTA', 2, 2, 430, 600, 170, 'ACTIVO', 1),
(238, '7801430430121', 'ARROZ GRADO1 500G', 'ARUBA', 4, 2, 520, 900, 380, 'ACTIVO', 1),
(239, '6959237325841', 'SOPA INSTANTANEA DE FIDEOS', 'VITTELLI', 3, 2, 430, 600, 170, 'ACTIVO', 1),
(240, '7802575000552', 'CARACOQUESOS 296G', 'CAROZZI', 2, 2, 990, 1300, 310, 'ACTIVO', 1),
(241, '7802500221663', 'LASAÑA PRECOCIDA ', 'LUCCHETTI', 3, 2, 1020, 1450, 430, 'ACTIVO', 1),
(242, '7804655342491', 'PURÉ DE PAPAS 250G', 'SPECIA', 3, 2, 500, 900, 400, 'ACTIVO', 1),
(243, '7802615007848', 'ARROZ PRIMAVERA PREPARADO', 'MIRAFLORES', 1, 1, 740, 1100, 360, 'ACTIVO', 1),
(244, '7802615008036', 'ARROZ PAELLA PREPARADO', 'MIRAFLORES', 1, 1, 740, 1100, 360, 'ACTIVO', 1),
(245, '7802615007985', 'ARROZ CHAMPIÑON PREPARADO', 'MIRAFLORES', 1, 1, 740, 1100, 360, 'ACTIVO', 1),
(246, '7613032517748', 'LECHE NIDO BUEN DIA 130gr', 'NESTLÉ', 4, 2, 729, 990, 261, 'ACTIVO', 1),
(247, '7613037484540', 'MILO 230gr', 'NESTLÉ', 2, 1, 990, 1400, 410, 'ACTIVO', 1),
(248, '7613036838191', 'NESQUIK SABOR FRUTILLA 180g', 'NESTLÉ', 2, 1, 790, 1300, 510, 'ACTIVO', 1),
(249, '7613036892964', 'NESQUIK SABOR CHOCOLATE 180g', 'NESTLÉ', 2, 1, 790, 1300, 510, 'ACTIVO', 1),
(250, '7801810712663', 'TÉ YELLOW LABEL', 'LIPTON', 3, 2, 990, 1300, 310, 'ACTIVO', 1),
(251, '5900396011022', 'TÉ NEGRO', 'JUST A MINUTE', 4, 2, 490, 750, 260, 'ACTIVO', 1),
(252, '7801875058072', 'TÉ DE MENTA', 'SUPREMO', 1, 1, 759, 1100, 341, 'ACTIVO', 1),
(253, '7801875061010', 'TÉ HIERBAS SURTIDAS', 'SUPREMO', 1, 1, 720, 1100, 380, 'ACTIVO', 1),
(254, '7801875058164', 'TÉ MANZANILLA MIEL', 'SUPREMO', 1, 1, 720, 1100, 380, 'ACTIVO', 1),
(255, '7801875055132', 'TÉ SABOR MANZANA Y CANELA', 'SUPREMO', 1, 1, 1150, 1450, 300, 'ACTIVO', 1),
(256, '7801875055019', 'TÉ SABOR CANELA', 'SUPREMO', 1, 1, 1170, 1450, 280, 'ACTIVO', 1),
(257, '7801810804467', 'TÉ VERDE', 'LIPTON', 1, 1, 1000, 1350, 350, 'ACTIVO', 1),
(258, '7805040413154', 'CHANCACA 450g', 'DELICIOSA', 2, 1, 1890, 2300, 410, 'ACTIVO', 1),
(259, '7613032836740', 'CAFÉ DESCAFEINADO 50g', 'NESCAFÉ', 2, 1, 1750, 2100, 350, 'ACTIVO', 1),
(260, '7613032203122', 'CAFÉ TRADICION GRANULADO 50g', 'NESCAFÉ', 5, 2, 1440, 1890, 450, 'ACTIVO', 1),
(261, '7802800500772', 'CAFÉ PREMIER GRANULADO 50g', 'GOLD', 2, 1, 1150, 1690, 540, 'ACTIVO', 1),
(262, '7804634400259', 'QUESO GAUDA LAMINADO', 'RUMAY', 2, 1, 4990, 6490, 1500, 'ACTIVO', 11),
(263, '7802950009262', 'CAFÉ DOLCA 50g', 'NESCAFE	', 2, 1, 1070, 1450, 380, 'ACTIVO', 1),
(264, '7802950008715', 'CAFÉ ECO 50g', 'NESTLÉ', 4, 2, 705, 990, 285, 'ACTIVO', 1),
(265, '7802950002133', 'CAFÉ TRADICION 100g', 'NESCAFÉ', 2, 1, 2730, 3550, 820, 'ACTIVO', 1),
(266, '7802950002324', 'CAFÉ ECO 170g', 'NESTLE', 2, 1, 1500, 1800, 300, 'ACTIVO', 1),
(267, '7801875047113', 'TÉ CEYLAN PREMIUM', 'SUPREMO', 3, 1, 950, 1250, 300, 'ACTIVO', 1),
(268, '7805000306533', 'TÉ NEGRO 20b', 'CLUB', 3, 1, 500, 750, 250, 'ACTIVO', 1),
(269, '7805000306366', 'TÉ NEGRO 100B (CAJA NEGRA)', 'EMBLEM TEA', 1, 1, 1890, 2400, 510, 'ACTIVO', 1),
(270, '7801810117543', 'TÉ NEGRO 100B (CAJA AMARILLA) ', 'EMBLEM TEA', 1, 1, 1890, 2350, 460, 'ACTIVO', 1),
(271, '7801615775504', 'JUGO EN POLVO PINA ', 'TURBO', 9, 3, 101, 150, 49, 'ACTIVO', 1),
(272, '7802800578900', 'JUGO EN POLVO HUESILLO', 'ZUKO', 10, 3, 146, 170, 24, 'ACTIVO', 1),
(273, '7802575223456', 'JUGO EN POLVO FRUTILLA', 'SPRIM', 6, 3, 119, 200, 81, 'ACTIVO', 1),
(274, '7802955009960', 'LECHE CAPPUCCINO 1L', 'DANNONE', 1, 1, 1458, 1990, 532, 'ACTIVO', 1),
(275, '7802955009984', 'LECHE CARAMEL CAFÉ 1L', 'DANONE', 1, 1, 1458, 1990, 532, 'ACTIVO', 1),
(276, '7802955009977', 'LECHE MOCACCINO 1L', 'DANONE', 1, 1, 1458, 1990, 532, 'ACTIVO', 1),
(277, '7802920777542', 'LECHE ENTERA 1L', 'COLUN', 3, 2, 890, 1190, 300, 'ACTIVO', 1),
(278, '7808709500431', 'LECHE ENTERA 1L', 'LIDER', 2, 2, 679, 900, 221, 'ACTIVO', 1),
(279, '7808709500455', 'LECHE SEMI DESCREMADA 1L', 'LIDER', 3, 2, 679, 900, 221, 'ACTIVO', 1),
(280, '7808709500448', 'LECHE DESCREMADA 1L', 'LIDER', 3, 2, 679, 900, 221, 'ACTIVO', 1),
(281, '7802900480042', 'JUGO NECTAR DAMASCO 1L', 'SOPROLE', 1, 1, 530, 850, 320, 'ACTIVO', 1),
(282, '7802900480035', 'JUGO NECTAR PIÑA 1L', 'SOPROLE', 2, 1, 530, 850, 320, 'ACTIVO', 1),
(283, '7802900480134', 'JUGO NECTAR DURAZNO 1L', 'SOPROLE', 2, 1, 530, 850, 320, 'ACTIVO', 1),
(284, '7802900480066', 'JUGO NECTAR NARANJA 1L', 'SOPROLE', 1, 1, 530, 850, 320, 'ACTIVO', 1),
(285, '7802920007410', 'LECHE SIN LACTOSA SEMIDESCREMADA 1L', 'COLUN', 2, 1, 881, 1190, 309, 'ACTIVO', 1),
(286, '7802920007519', 'LECHE SIN LACTOSA CHOCOLATE 1L', 'COLUN', 1, 1, 1170, 1690, 520, 'ACTIVO', 1),
(287, '7802920007120', 'LECHE CHOCOLATE 1L', 'COLUN', 3, 2, 950, 1350, 400, 'ACTIVO', 1),
(288, '7802900002381', 'LECHE PROTEIN+ 1L', 'SOPROLE', 1, 1, 1450, 1790, 340, 'ACTIVO', 1),
(289, '7708661000010', 'LECHE DE ARROZ NATURAL 1L ', 'NICE DAY', 2, 1, 989, 1750, 761, 'ACTIVO', 1),
(290, '5002', 'CAJA FOSFOROS  ', 'ACUENTA', 20, 5, 60, 100, 40, 'ACTIVO', 1),
(291, '9315861128888', 'PACK 10 CAJAS FOSFOROS ', 'PAVO REAL', 2, 1, 350, 600, 250, 'ACTIVO', 1),
(292, '7804603463612', 'FOSFOROS 150u', 'PUERTO VARAS', 5, 2, 890, 500, -390, 'ACTIVO', 1),
(293, '5001', 'FOSFOROS', 'LIDER', 20, 5, 90, 120, 30, 'ACTIVO', 1),
(294, '7802630002187', 'SOPA INDIVIDUAL POLLO ', 'LIDER', 16, 4, 180, 300, 120, 'ACTIVO', 1),
(295, '7613033609992', 'SOPA PARA UNO ESPARRAGO ', 'MAGGI', 15, 5, 180, 300, 120, 'ACTIVO', 1),
(296, '7613032901912', 'SOPA PARA UNO POLLO-MERKEN', 'MAGGI', 7, 4, 200, 400, 200, 'ACTIVO', 1),
(297, '7802630002163', 'SOPA INDIVUDAL TOMATE', 'LIDER', 4, 3, 180, 300, 120, 'ACTIVO', 1),
(298, '7802575262141', 'FLAN CHOCOLATE', 'CARICIA', 2, 1, 650, 850, 200, 'ACTIVO', 1),
(299, '7802575262158', 'FLAN VAINILLA', 'CARICIA', 2, 1, 650, 850, 200, 'ACTIVO', 1),
(300, '7802200361089', 'GELATINA NARANJA', 'AMBROSOLI', 2, 1, 580, 800, 220, 'ACTIVO', 1),
(301, '7802200361096', 'GELATINA PIÑA', 'AMBROSOLI', 1, 1, 580, 800, 220, 'ACTIVO', 1),
(302, '7802410820154', 'SALSA DE SOYA', 'LA PRIMERA', 4, 2, 630, 890, 260, 'ACTIVO', 1),
(303, '7802410100249', 'SALSA DE SOYA 1L', 'ACUENTA', 2, 1, 1480, 1790, 310, 'ACTIVO', 1),
(304, '7802575365026', 'SALSA TOMATES ITALIANA', 'SAN REMO', 6, 3, 259, 400, 141, 'ACTIVO', 1),
(305, '7801800102559', 'SALSA TOMATES ITALIANA', 'ACUENTA', 5, 3, 175, 300, 125, 'ACTIVO', 1),
(306, '7802337976026', 'MOSTAZA 250g', 'TRAVERSO', 2, 1, 450, 650, 200, 'ACTIVO', 1),
(307, '7802337930462', 'AJÍ CHILENO (CHORIPACK)', 'TRAVERSO', 2, 1, 1000, 1350, 350, 'ACTIVO', 1),
(308, '7802337976057', 'KETCHUP 250g', 'TRAVERSO', 2, 1, 550, 850, 300, 'ACTIVO', 1),
(309, '7802640720538', 'KETCHUP 500g', 'HELMANNS', 2, 1, 1290, 1590, 300, 'ACTIVO', 1),
(310, '7809562401217', 'CEREZO MARRASQUINO 250g', 'PERELLO', 6, 2, 1490, 2100, 610, 'ACTIVO', 1),
(311, '7802337930219', 'AJÍ CHILENO', 'TRAVERSO', 2, 1, 570, 790, 220, 'ACTIVO', 1),
(312, '7802950012316', 'SALSA DE TOMATE CARNE', 'MAGGI', 6, 2, 790, 1240, 450, 'ACTIVO', 1),
(313, '7802351001872', 'MOSTAZA 250g', 'ACUENTA', 1, 1, 610, 730, 120, 'ACTIVO', 1),
(314, '7802351001926', 'KETCHUP 240g', 'ACUENTA', 2, 1, 610, 730, 120, 'ACTIVO', 1),
(315, '7804635930694', 'GALLETAS DE ARROZ CON ESTEVIA Y SUCRALOSA', 'LIDER', 1, 1, 1090, 1400, 310, 'ACTIVO', 1),
(316, '7804635930700', 'GALLETAS DE ARROZ TOQUE DE SAL', 'LIDER', 1, 1, 1090, 1400, 310, 'ACTIVO', 1),
(317, '7801420001782', 'GALLETA DE ARROZ DULCES', 'TUCAPEL', 1, 1, 1000, 1500, 500, 'ACTIVO', 1),
(318, '7801420001775', 'GALLETAS DE ARROZ CLASICAS', 'TUCAPEL', 1, 1, 1000, 1500, 500, 'ACTIVO', 1),
(319, '7802215301346', 'CEREAL BAR (CHOCOCEREAL)', 'COSTA', 1, 1, 1000, 1490, 490, 'ACTIVO', 1),
(320, '7802215303371', 'CEREAL BAR (FRUTOS ROJOS)', 'COSTA', 1, 1, 1000, 1490, 490, 'ACTIVO', 1),
(321, '7613037008470', 'NESTUM FRUTILLA', 'NESTLE', 1, 1, 1100, 1400, 300, 'ACTIVO', 1),
(322, '7613037008449', 'NESTUM TRIGO Y MIEL', 'NESTLE', 1, 1, 1100, 1400, 300, 'ACTIVO', 1),
(323, '7802351624002', 'MAYONESA CLASICA', 'DON JUAN', 11, 3, 270, 490, 220, 'ACTIVO', 1),
(324, '7805000312329', 'MAYONESA 670g', 'HELMANNS', 2, 1, 1390, 2300, 910, 'ACTIVO', 1),
(325, '7803200804132', 'MAYONESA 372g', 'HELMANNS', 2, 1, 1500, 1850, 350, 'ACTIVO', 1),
(326, '7802337500016', 'MAYONESA BIG PARTY', 'TRAVERSO', 2, 1, 1160, 1850, 690, 'ACTIVO', 1),
(327, '7804659650264', 'MAYONESA VEGETAL', 'NOT MAYO', 2, 1, 1490, 1750, 260, 'ACTIVO', 1),
(328, '7802410003250', 'ALIÑO COMPLETO', 'LIDER', 4, 2, 170, 300, 130, 'ACTIVO', 1),
(329, '7802410003298', 'CANELA MOLIDA', 'LIDER', 2, 1, 310, 450, 140, 'ACTIVO', 1),
(330, '7802410003274', 'BICARBONATO DE SODIO', 'LIDER', 3, 2, 120, 300, 180, 'ACTIVO', 1),
(331, '7802410003281', 'CANELA ENTERA', 'LIDER', 2, 1, 470, 550, 80, 'ACTIVO', 1),
(332, '7802410003335', 'OREGANO ENTERO', 'LIDER', 2, 1, 270, 450, 180, 'ACTIVO', 1),
(333, '7809557000814', 'CLAVO DE OLOR', 'NEGRITA', 2, 1, 170, 300, 130, 'ACTIVO', 1),
(334, '7801434001242', 'PIMIENTA BLANCA', 'NEGRITA', 2, 1, 360, 450, 90, 'ACTIVO', 1),
(335, '7801434001471', 'NUEZ MOSCADA', 'NEGRITA', 2, 1, 240, 350, 110, 'ACTIVO', 1),
(336, '7801434001150', 'CURRY EN POLVO', 'NEGRITA', 2, 1, 155, 300, 145, 'ACTIVO', 1),
(337, '7801434001112', 'COMINO MOLIDO', 'NEGRITA', 2, 1, 350, 490, 140, 'ACTIVO', 1),
(338, '7801434001433', 'BICARBONATO', 'NEGRITA', 1, 1, 420, 550, 130, 'ACTIVO', 1),
(339, '7801434001143', 'LAUREL EN HOJAS', 'NEGRITA', 2, 1, 240, 400, 160, 'ACTIVO', 1),
(340, '7801434000993', 'AJO EN POLVO', 'NEGRITA', 2, 1, 180, 350, 170, 'ACTIVO', 1),
(341, '7801434001044', 'CANELA', 'NEGRITA', 2, 1, 350, 490, 140, 'ACTIVO', 1),
(342, '7801434000955', 'AJO DE COLOR', 'NEGRITA', 3, 1, 260, 320, 60, 'ACTIVO', 1),
(343, '7801434001211', 'OREGANO MOLIDO', 'NEGRITA', 2, 1, 155, 300, 145, 'ACTIVO', 1),
(344, '7801434001846', 'OREGANO ENTERO', 'NEGRITA', 2, 1, 240, 400, 160, 'ACTIVO', 1),
(345, '7801800107158', 'ARVERJAR 420', 'ACONCAGUA', 4, 1, 990, 1290, 300, 'ACTIVO', 1),
(346, '7804624810020', 'PALMITOS 400g', 'LIDER', 3, 1, 1150, 1550, 400, 'ACTIVO', 1),
(347, '7807900005219', 'CHAMPIÑONES LAMINADOS 184', 'LIDER', 3, 1, 650, 950, 300, 'ACTIVO', 1),
(348, '7801235131117', 'JUREL AL NATURAL', 'SAN JOSE', 3, 1, 1171, 1550, 379, 'ACTIVO', 1),
(349, '7807900005196', 'CHAMPIÑONES LAMINADOS 400g', 'LIDER', 3, 1, 1100, 1400, 300, 'ACTIVO', 1),
(350, '7801235276115', 'CHORITOS AL NATURAL', 'SANJOSE', 3, 1, 1050, 1350, 300, 'ACTIVO', 1),
(351, '7801255000318', 'CHORITOS EN ACEITE', 'COLORADO', 3, 1, 1000, 1300, 300, 'ACTIVO', 1),
(352, '7801235001878', 'ATUN LOMITOS EN AGUA ', 'SAN JOSE', 1, 1, 1000, 1300, 300, 'ACTIVO', 1),
(353, '7804608223082', 'LOMITOS DE ATUN EN AGUA', 'DORASOL', 3, 1, 1000, 1300, 300, 'ACTIVO', 1),
(354, '7804608221583', 'JUREL', 'DORASOL', 3, 1, 990, 1300, 310, 'ACTIVO', 1),
(355, '6000', 'LONGANIZA DE CHILLAN', 'OHIGGINS', 1, 1, 2500, 3200, 700, 'ACTIVO', 11),
(356, '7801927000349', 'JAMON  SANDWICH', 'SOLER', 2, 1, 1280, 1790, 510, 'ACTIVO', 11),
(357, '7801900002247', 'JAMON PIERNA ARTESANAL', 'WINTER', 1, 1, 1390, 1890, 500, 'ACTIVO', 11),
(358, '7801900082065', 'JAMON ACARAMELADO', 'WINTER', 1, 1, 1790, 2290, 500, 'ACTIVO', 11),
(359, '7804945011441', 'ALCOHOL GEL ', 'SIMONDS', 4, 2, 1000, 1400, 400, 'ACTIVO', 11),
(360, '7802900230227', 'YOGHURT D FRUTILLA 120 GRS REQUETEPATITAS', 'SOPROLE', 10, 4, 170, 220, 50, 'ACTIVO', 11),
(361, '7802900230289', 'YOGHURT DE FRAMBUEZA  REQUETEPATITAS', 'SOPROLE', 4, 2, 170, 220, 50, 'ACTIVO', 11),
(362, '7802900230258', 'YOGHURT  DE VAINILLA  REQUETEPATITAS', 'SOPROLE', 4, 2, 170, 220, 50, 'ACTIVO', 11),
(363, '7613033458538', 'CAFE VAINILLA LATTE  148 GRS  SOBRE', 'NESCAFE', 8, 2, 300, 450, 150, 'ACTIVO', 1),
(364, '7613033458101', 'CAFE CAPUCCINO SOBRE', 'NESCAFE', 10, 2, 300, 450, 150, 'ACTIVO', 1),
(365, '7613033527173', 'CAFE DOBLE CHOCA MOKA SOBRE', 'NESCAFE', 8, 2, 300, 450, 150, 'ACTIVO', 1),
(366, '7801800000022', 'DURAZNO CUBITOS 580G', 'ACONCAGUA', 3, 1, 729, 1300, 571, 'ACTIVO', 1),
(367, '7613032414580', 'CREMA DE LECHE 157 GRS. TARRO', 'NESTLE', 5, 2, 690, 950, 260, 'ACTIVO', 1),
(368, '7803010034453', 'CREMA CHANTYMIX 400 GRS', 'COLLICO', 2, 1, 1820, 2290, 470, 'ACTIVO', 1),
(369, '7802900097011', 'CREMA CHANTILLY  1,5 LITROS  SPRAY', 'SOPROLE', 2, 1, 2500, 2990, 490, 'ACTIVO', 11),
(370, '7802215512278', 'GALLETAS  FRAC  CHOCOLATE 130 GRS', 'COSTA', 4, 2, 370, 490, 120, 'ACTIVO', 1),
(371, '7802215512261', 'GALLETA FRAC CLASICA 130 GRS', 'COSTA', 3, 2, 370, 490, 120, 'ACTIVO', 1),
(372, '7802215512292', 'GALLETA FRAC BI CAPUCHINO 130GRS', 'COSTA', 5, 2, 370, 490, 120, 'ACTIVO', 1),
(373, '7802215512308', 'GALLETA FRAC FRUTILLA ', 'COSTA', 4, 2, 370, 490, 120, 'ACTIVO', 1),
(374, '5000', 'JAMON  SANDWICH ', 'PF', 1, 1, 1820, 2500, 680, 'ACTIVO', 11),
(375, '7801930006420', 'VIENESAS DE POLLO SUREÑAS 5 UNIDADES', 'PF', 5, 2, 510, 790, 280, 'ACTIVO', 11),
(376, '7801930005782', 'JAMON PIERNA 8 LAMINAS', 'PF', 2, 1, 1250, 1690, 440, 'ACTIVO', 11),
(377, '7804634400259', 'QUESO GAUDA LAMINADO', 'RUMAY', 2, 1, 4990, 6500, 1510, 'ACTIVO', 11),
(378, '7801930012407', 'CHURRASCO DE VACUNO  120 GRS ', 'PF', 10, 2, 580, 900, 320, 'ACTIVO', 6),
(379, '7802950005943', 'CALDO DE GALLINA  12 TABLETAS', 'MAGGI', 5, 2, 850, 1150, 300, 'ACTIVO', 1),
(380, '7805660002073', 'CAJA PLASTICA TRANSPARENTE 6 LITROS', 'WENCO', 30, 5, 663, 1350, 687, 'ACTIVO', 5),
(381, '400006379948', 'SALPIMENTERO REDONDO VIDRIO', 'HAUS', 3, 1, 790, 1190, 400, 'ACTIVO', 5),
(382, '400006379948', 'SALPIMENTERO VIDRIO ', 'HAUS', 3, 1, 790, 1190, 400, 'ACTIVO', 5),
(383, '7702147247791', 'JUEGO DE 6 COPAS DE VINO', 'CRISTAL', 1, 1, 3490, 3990, 500, 'ACTIVO', 5),
(384, '7501059299245', 'COFFEE MATE   CREMA PARA CAFE ORIGINAL 435 GRS', 'NESTLE', 5, 2, 2150, 2790, 640, 'ACTIVO', 1),
(385, '7807210663444', 'SET  DE PINCELES  2 4 8', 'ARTEL', 2, 1, 1190, 1500, 310, 'ACTIVO', 7),
(386, '7801365000284', 'CHAMPIÑONENTERO NATURAL', 'CHAMPIÑON', 2, 1, 1090, 1300, 210, 'ACTIVO', 7),
(387, '7793670000052', 'YERBA MATE  HIERBAS SERRANA', 'VERDE FLOR', 1, 1, 2000, 2350, 350, 'ACTIVO', 1),
(388, '7793670000632', 'YERBA MATE NARANJA', 'VERDE FLOR', 1, 1, 2000, 2350, 350, 'ACTIVO', 1),
(389, '7793670400517', 'YERBA MATE CEDRON', 'VERDE FLOR', 1, 1, 2000, 2350, 350, 'ACTIVO', 1),
(390, '7804605470212', 'YERBA MATE SUPERIOR', 'ISABELLE', 2, 1, 1140, 1590, 450, 'ACTIVO', 1),
(391, '7801840000105', 'YERBA MATE  CON PALO  250 GRS', 'MAPUCHE', 2, 1, 1120, 1700, 580, 'ACTIVO', 1),
(392, '7802920000435', 'CREMA PARA BATIR 1L', 'COLUN', 2, 1, 2390, 2690, 300, 'ACTIVO', 1),
(393, '7802920221328', 'MANJAR TRADICIONAL 400G', 'COLUN', 5, 2, 940, 1350, 410, 'ACTIVO', 1),
(394, '7613030049883', 'LECHE CONDENSANDA 397', 'NESTLE', 3, 1, 1039, 1250, 211, 'ACTIVO', 1),
(395, '7708634000016', 'LECHE CONDENSADA 1K', 'PEDREGAL', 3, 1, 1392, 2300, 908, 'ACTIVO', 1),
(396, '7708409000012', 'LECHE CONDENSADA 390G', 'PEDREGAL', 4, 2, 590, 990, 400, 'ACTIVO', 1),
(397, '7752087774932', 'LECHE EVAPORADA IDEAL 400G', 'NESTLE ', 3, 1, 1000, 1300, 300, 'ACTIVO', 1),
(398, '7613037077667', 'MANJAR RETRO 380G', 'NESTLE', 3, 1, 981, 1490, 509, 'ACTIVO', 1),
(399, '7804611550687', 'DURAZNO EN CUBITOS 567G', 'EL JARDIN', 3, 1, 952, 1250, 298, 'ACTIVO', 1),
(400, '7801800000022', 'DURAZNO CUBITOS 580G', 'ACONCAGUA', 3, 1, 729, 1250, 521, 'ACTIVO', 1),
(401, '7804611550168', 'PIÑAS EN RODAJAS  565G', 'NOVAFRUTA', 3, 1, 889, 1350, 461, 'ACTIVO', 1),
(402, '7804000001240', 'MERMELADA DE FRUTILLA LIGHT', 'TIA LIA', 1, 1, 750, 990, 240, 'ACTIVO', 1),
(403, '7804000001219', 'MERMELADA DE DAMASCO', 'TIA LIA', 1, 1, 750, 990, 240, 'ACTIVO', 1),
(404, '7804000001233', 'MERMELADA DE MORA', 'TIA LIA', 1, 1, 750, 990, 240, 'ACTIVO', 1),
(405, '7809562401309', 'MERMELADA DE DURAZNO', 'PERELLO', 3, 1, 390, 550, 160, 'ACTIVO', 1),
(406, '7809562401309', 'MERMELADA DE CIRUELA', 'PERELLO', 1, 1, 390, 550, 160, 'ACTIVO', 1),
(407, '7804000000557', 'MERMELADA MORA', 'ACUENTA', 1, 1, 340, 500, 160, 'ACTIVO', 1),
(408, '7804000000564', 'MERMELADA FRUTILLA', 'ACUENTA', 1, 1, 340, 500, 160, 'ACTIVO', 1),
(409, '7804000000533', 'MERMELADA DURAZNO', 'ACUENTA', 1, 1, 340, 500, 160, 'ACTIVO', 1),
(410, '78028100310203', 'MERMELADA DURAZNO', 'WATTS', 1, 1, 890, 1190, 300, 'ACTIVO', 1),
(411, '7802810031013', 'MERMELADA DAMASCO', 'WATTS', 2, 1, 890, 1190, 300, 'ACTIVO', 1),
(412, '7802810005632', 'MERMELADA FRUTAS DEL SUR', 'WATTS', 1, 1, 890, 1190, 300, 'ACTIVO', 1),
(413, '7804000002285', 'MERMELADA DE FRAMBUEZA', 'HONESTA', 2, 1, 790, 1100, 310, 'ACTIVO', 1),
(414, '7804000002278', 'MERMELADA DE DURAZNO', '', 2, 1, 790, 1100, 310, 'ACTIVO', 1),
(415, '7613035311565', 'CAFE 3 EN 1', 'NESCAFE', 8, 3, 210, 300, 90, 'ACTIVO', 1),
(416, '5003', 'CAFE EN SOBRE 1.8G', 'NESCAFE', 94, 10, 100, 150, 50, 'ACTIVO', 1),
(417, '7802215508523', 'DONUTS', 'COSTA', 3, 1, 750, 950, 200, 'ACTIVO', 1),
(418, '7802230086952', 'GALLETAS TRITON VAINILLA', 'MCKAY', 5, 2, 457, 650, 193, 'ACTIVO', 1),
(419, '7613032789480', 'GALLETAS DE MARAVILLA', 'MCKAY', 2, 1, 490, 690, 200, 'ACTIVO', 1),
(420, '7613032589714', 'GALLETAS DE LIMON', 'MCKAY', 3, 1, 490, 690, 200, 'ACTIVO', 1),
(421, '7613032590369', 'GALLETAS NIZA', 'MCKAY', 3, 1, 490, 690, 200, 'ACTIVO', 1),
(422, '7802230083951', 'GALLETAS DE MANTEQUILLA', 'MCKAY					', 3, 1, 490, 690, 200, 'ACTIVO', 1),
(423, '7802215504679', 'NIK LIMON', 'COSTA', 4, 2, 325, 450, 125, 'ACTIVO', 1),
(424, '7802215504655', 'NIK BOCADO', 'COSTA	', 6, 2, 325, 450, 125, 'ACTIVO', 1),
(425, '7802215504662', 'NIK FRUTILLA', 'COSTA', 4, 2, 325, 450, 125, 'ACTIVO', 1),
(426, '7896058258134', 'OBLEAS DE CHOCOLATE', 'DOS EN UNO ', 3, 2, 390, 550, 160, 'ACTIVO', 1),
(427, '7802215615085', 'TRIPACK CRACKELET', 'COSTA', 2, 1, 1300, 850, -450, 'ACTIVO', 1),
(428, '7802408002722', 'SUSTANCIAS', 'FRUNA', 48, 7, 96, 150, 54, 'ACTIVO', 1),
(429, '78023994', 'BON O BON AMARILLO', 'ARCOR', 20, 5, 98, 150, 52, 'ACTIVO', 1),
(430, '77956712', 'BON O BON MORADO', 'ARCOR', 15, 5, 98, 150, 52, 'ACTIVO', 1),
(431, '78023215', 'BON O BON CAFE', 'ARCOR', 2, 5, 98, 150, 52, 'ACTIVO', 1),
(432, '5004', 'COLLAC', 'SUPER', 17, 5, 80, 100, 20, 'ACTIVO', 1),
(433, '7802215516207', 'MINI PALMERITAS', 'COSTA', 6, 2, 155, 250, 95, 'ACTIVO', 1),
(434, '7802215516214', 'MINI GALLETAS VINO', 'COSTA', 6, 3, 155, 250, 95, 'ACTIVO', 1),
(435, '7802225614498', 'DINOSAURIOS', 'ARCOR', 3, 2, 155, 250, 95, 'ACTIVO', 1),
(436, '7802215502286', 'MINI DONUTS', 'COSTA', 2, 2, 250, 250, 0, 'ACTIVO', 1),
(437, '7802225680448', 'GALLETAS FESTIDULCE', 'ARCOR', 1, 1, 155, 250, 95, 'ACTIVO', 1),
(438, '7803403003103', 'PAN BLANCO', 'IDEAL', 0, 1, 1900, 2200, 300, 'ACTIVO', 1),
(439, '7803403000331', 'RAPIDITAS CLASICAS', 'IDEAL', 4, 1, 800, 1100, 300, 'ACTIVO', 1),
(440, '7802636001979', 'PRE PIZZA TRADICIONAL', 'LIDER', 1, 1, 1290, 1550, 260, 'ACTIVO', 1),
(441, '7806783813379', 'LAPIZ SCRIPTO', 'EXLIN', 2, 1, 1000, 1200, 200, 'ACTIVO', 7),
(442, '3474370505040', 'MINA 0.5', 'PENTREL', 1, 1, 250, 300, 50, 'ACTIVO', 7),
(443, '3474370507020', 'MINA 0.7', 'PENTREL', 1, 1, 270, 350, 80, 'ACTIVO', 7),
(444, '4017', 'MINA 0.9', 'ATLAS', 1, 1, 250, 380, 130, 'ACTIVO', 7),
(445, '7805670168950', 'VELAS FROZEN', 'ARGOS', 1, 1, 1690, 1890, 200, 'ACTIVO', 5),
(446, '7805670169001', 'VELAS SPIDER-MANN', 'ARGOS', 1, 1, 1690, 1890, 200, 'ACTIVO', 5),
(447, '6932265178221', 'SET LAPICES Y GOMA', 'ANIMAL FRIENDS', 2, 1, 1200, 1400, 200, 'ACTIVO', 7),
(448, '6871128200078', 'BOLAS DE POREXPAN 5.0- 4PCS', 'DREAM DIY', 1, 1, 500, 690, 190, 'ACTIVO', 7),
(449, '6871128200030', 'BOLAS DE POREXPAN 3.0- 8PCS', 'DREAM DIY', 1, 1, 500, 690, 190, 'ACTIVO', 7),
(450, '1681688224408', 'ACUARELAS', 'PINMARK', 2, 1, 800, 950, 150, 'ACTIVO', 7),
(451, '7807265009587', 'TIJERAS NARANJA', 'PROARTE ', 1, 1, 700, 850, 150, 'ACTIVO', 7),
(452, '7807210640100', 'COLA FRIA ', 'ARTEL', 2, 1, 1090, 1400, 310, 'ACTIVO', 7),
(453, '7807210025556', 'PEGAMENTO TRANSPARENTE', 'ARTEL			', 2, 1, 1150, 1450, 300, 'ACTIVO', 7),
(454, '8854556000180', 'CORRECTOR LIQUID PAPER', 'PAPER MATE', 7, 2, 750, 990, 240, 'ACTIVO', 7),
(455, '8802966060201', 'CORCHETES 23/8', 'WHASHIN', 2, 1, 760, 890, 130, 'ACTIVO', 7),
(456, '7807210650079', 'PEGAMENTO EN BARRA (STICK)', 'ARTEL', 3, 1, 550, 650, 100, 'ACTIVO', 7),
(457, '6942139318837', 'CRAYONES 6', 'COLOR CRAYONS', 2, 1, 500, 650, 150, 'ACTIVO', 7),
(458, '6989600281917', 'LIBRETA DE FLORES', 'SWEET FLORES', 1, 1, 2200, 2500, 300, 'ACTIVO', 5),
(459, '7808304262628', 'VELAS', 'GLAM', 1, 1, 1000, 1300, 300, 'ACTIVO', 5),
(460, '7808304262635', 'VELAS', 'GLAM', 1, 1, 1000, 1300, 300, 'ACTIVO', 5),
(461, '7805670702833', 'VELAS MAGICAS ', 'ARGOS', 2, 1, 750, 890, 140, 'ACTIVO', 5),
(462, '6920190230060', 'SET ESCOLAR', '', 1, 1, 1300, 1600, 300, 'ACTIVO', 7),
(463, '40127378', 'LAPIZ ADHESIVI', 'PELIFLIX', 2, 1, 200, 250, 50, 'ACTIVO', 7),
(464, '7807265098697', 'PLUMONES PUNTA DELGADA', 'PROARTE', 2, 1, 1000, 1300, 300, 'ACTIVO', 7),
(465, '7806505041158', 'CORCHETE 26/6', 'TORRE', 3, 1, 750, 890, 140, 'ACTIVO', 7),
(466, '6953675370982', 'LAPICES DE COLORES', 'VBV', 2, 1, 660, 850, 190, 'ACTIVO', 7),
(467, '7801434001549', 'PALOS DE BROCHETAS 50U', 'NEGRITA', 1, 1, 700, 850, 150, 'ACTIVO', 7),
(468, '7807265090547', 'CUADERNO UNIVERSITARIO 100HJS', 'PROARTE', 10, 3, 1000, 1350, 350, 'ACTIVO', 5),
(469, '4018', 'BARRA DE SILICONA ', 'DREAM DIY', 5, 2, 70, 100, 30, 'ACTIVO', 5),
(470, '4018', 'SCOCH MEDIANO ', '400 SELLO FILM', 2, 1, 800, 990, 190, 'ACTIVO', 7),
(471, '7810265980435', 'PALOS DE HELADO 50U', 'PROARTE', 1, 1, 390, 490, 100, 'ACTIVO', 7),
(472, '4019', 'CD', 'DATACE', 6, 2, 180, 250, 70, 'ACTIVO', 7),
(473, '7806505040908', 'CLIPS 33MM', 'TORRE', 4, 1, 670, 790, 120, 'ACTIVO', 7),
(474, '6938485481257', 'ESTUCHE DE ALUMINIO', 'HAPPY BEAR', 2, 1, 1350, 1590, 240, 'ACTIVO', 7),
(475, '4020', 'GOMA DE BORRAR ', 'MINI TECHNIC PROARTE', 5, 2, 130, 200, 70, 'ACTIVO', 7),
(476, '7807210663444', 'SET PINCELES N2-4-8', 'ARTEL', 1, 1, 1200, 1500, 300, 'ACTIVO', 7),
(477, '4021', 'NOTAS ADHESIVAS', '', 2, 1, 350, 500, 150, 'ACTIVO', 7),
(478, '4022', 'GOMA EVA ', 'PURPURINA', 6, 2, 180, 250, 70, 'ACTIVO', 7),
(479, '4023', 'GOMA EVA ', 'ARTECOLOR', 6, 2, 200, 300, 100, 'ACTIVO', 7),
(480, '4024', 'HOJA DE BLOCK', 'ARTEL', 20, 5, 60, 100, 40, 'ACTIVO', 7),
(481, '4025', 'LAPIZ PASTA NEGRO', '', 6, 2, 100, 150, 50, 'ACTIVO', 7),
(482, '4026', 'LAPIZ PASTA AZUL', '', 10, 3, 100, 150, 50, 'ACTIVO', 7),
(483, '7807332027001', 'AMPOLLETA LED BOLA 90W', 'WESTINGHOUSE', 5, 2, 1300, 1600, 300, 'ACTIVO', 4),
(484, '4897033227575', 'CONFITES', 'MABU	', 4, 2, 1190, 1490, 300, 'ACTIVO', 1),
(485, '4897033226196', 'CHERIR CHOCOLATE ', 'FOR YOU', 4, 1, 790, 1000, 210, 'ACTIVO', 1),
(486, '4897033227568', 'CONFITES CHICO', 'MABU', 4, 1, 900, 1200, 300, 'ACTIVO', 1),
(487, '7807332027001', 'AMPOLLETA LED BOLA 90W', 'WESTINGHOUSE', 5, 2, 1300, 1600, 300, 'ACTIVO', 4),
(488, '606110013406', 'AMPOLLETA HALÓGENA 53W', 'BRIGH', 4, 2, 490, 600, 110, 'ACTIVO', 4),
(489, '7804660910029', 'AMPOLLETA 70W', 'MEGABRIGHT', 4, 2, 600, 750, 150, 'ACTIVO', 4),
(490, '7805560001022', 'TAPAGOTERA 250G', 'DYNAL', 5, 2, 3450, 3990, 540, 'ACTIVO', 4),
(491, '6915425000068', 'OVILLO CAÑAMO ', '', 5, 2, 700, 990, 290, 'ACTIVO', 4),
(492, '5605787002589', 'BRIDAS NAILON', 'ANTE', 3, 2, 1050, 1400, 350, 'ACTIVO', 4),
(493, '3000721300899', 'AMPOLLETA 26W', 'WESTINGHOUSE', 6, 2, 2000, 2500, 500, 'ACTIVO', 4),
(494, '7805300049154', 'INSECTISIDA MATA INSECTOS', 'TANAX', 2, 1, 2000, 2390, 390, 'ACTIVO', 4),
(495, '7805300049116', 'INSECTISIDA EN POLVO', 'TANAZ', 1, 1, 1600, 1990, 390, 'ACTIVO', 4),
(496, '4042448811653', 'CINTA AISLANTE ', 'TESA		', 3, 1, 1100, 1450, 350, 'ACTIVO', 4),
(497, '7805500010060', 'ADHESIVO DE CONTACTO ', 'AGOREX', 5, 2, 3000, 3500, 500, 'ACTIVO', 4),
(498, '7805300049307', 'RATICIDA', 'TANAX', 3, 1, 2100, 2500, 400, 'ACTIVO', 4),
(499, '7805300049598', 'INSECTISIDA AEREOSOL (ARAÑAS Y BARATAS)', 'TANAX', 2, 1, 2000, 2390, 390, 'ACTIVO', 4),
(500, '3006', 'PEGAFIX MADERAS', 'PRITT', 2, 1, 1100, 1400, 300, 'ACTIVO', 4),
(501, '8434485159991', 'BUSCAPOLO', '1MI STORE', 2, 1, 1800, 2100, 300, 'ACTIVO', 4),
(502, '7805020002385', 'PASTILLAS PARA ESTANQUE', 'EXCELL', 1, 1, 1600, 1900, 300, 'ACTIVO', 4),
(503, '7809826711601', 'TEFLOR', 'DURA GAS', 2, 1, 990, 1290, 300, 'ACTIVO', 4),
(504, '7528530002002', 'TEFLON', 'TAUMM', 2, 1, 600, 900, 300, 'ACTIVO', 4),
(505, '6932016090260', 'LED HEADLIGHT ROJA', 'SHUN FENG', 1, 1, 2100, 2490, 390, 'ACTIVO', 4),
(506, '6932016090260', 'LED HEADLINGHT AZUL', 'SHUN FENG ', 1, 1, 2100, 2490, 390, 'ACTIVO', 4),
(507, '7800678131425', 'MULTI MAGNETIC STRIPE', 'YINXIANG', 3, 1, 2500, 2800, 300, 'ACTIVO', 4),
(508, '2008', 'CEPILLO DE BAÑO', '', 1, 1, 1500, 1900, 400, 'ACTIVO', 2),
(509, '7806810014946', 'MOPA MULTIUSO ', 'VIRUTEX ', 1, 1, 3800, 4200, 400, 'ACTIVO', 2),
(510, '7802955009922', 'MOCACCINO', 'DANODE', 3, 2, 300, 400, 100, 'ACTIVO', 4),
(511, '7806395000051', 'DILUYENTE SINTETICO', 'PASSOL', 4, 1, 2600, 2990, 390, 'ACTIVO', 4),
(512, '7806395000082', 'BENCINA BLANCA', 'PASSOL', 2, 1, 2500, 2800, 300, 'ACTIVO', 4),
(513, '7805514000170', 'ACIDO MURIATICO', 'DIDEVAL', 2, 1, 1600, 1900, 300, 'ACTIVO', 4),
(514, '7419310093962', 'CARTUCHO A GAS PARA COCINILLA', 'PROVIDUS', 1, 1, 1600, 1990, 390, 'ACTIVO', 4),
(515, '7804930000191', 'LUSTRA MUEBLES TRADICIONAL', 'LIDER', 2, 1, 1150, 1590, 440, 'ACTIVO', 2),
(516, '7804930003499', 'LUSTRA MUEBLES VAINILLA ', 'LIDER	', 2, 1, 1150, 1590, 440, 'ACTIVO', 2),
(517, '5605787048327', 'SILICONA ACETICA GRIS', 'ANTE', 3, 1, 3000, 3500, 500, 'ACTIVO', 4),
(518, '5605787048310', 'SILICONA ACETICO BLANCO', 'ANTE', 2, 1, 3000, 3500, 500, 'ACTIVO', 4),
(519, '7809855025038', 'ENCHUFE MACHO SUPER ', 'KKALOP', 2, 1, 1400, 1790, 390, 'ACTIVO', 4),
(520, '7809855025182', 'ENCHUFE HEMBRA SUPER', 'KKALOP', 2, 1, 1400, 1790, 390, 'ACTIVO', 4),
(521, '8433845604270', 'PISTOLA DE SILICONA AZUL', 'BRICOTORO', 1, 1, 2500, 2900, 400, 'ACTIVO', 4),
(522, '7806786201012', 'CANDADO DE BICICLETA', '', 1, 1, 2800, 3100, 300, 'ACTIVO', 4),
(523, '5605787910051', 'INTERRUPTOR DE PASO ', 'ANTE', 1, 1, 1500, 1790, 290, 'ACTIVO', 4),
(524, '5605787910044', 'INTERRUPTOR DE PASO ', 'ANTE		', 2, 1, 1500, 1790, 290, 'ACTIVO', 4),
(525, '7800328758545', 'CINTA METRICA FLEXIBLE 7.5M', 'YALE', 1, 1, 3400, 3700, 300, 'ACTIVO', 4),
(526, '6939684819124', 'HUINCHA DE 5 METROS', 'HYPER TOUGH', 3, 1, 3700, 4000, 300, 'ACTIVO', 4),
(527, '5605787035334', 'FITA METRICA 3M', 'ANTE', 3, 1, 1900, 2200, 300, 'ACTIVO', 4),
(528, '6939684823732', 'JUEGO DE DESTORNILLADOR DE 2 PIEZAS', 'HYPER TOUGH', 1, 1, 2400, 2700, 300, 'ACTIVO', 4),
(529, '5605787402037', 'ESCOBILLA DE ACERO CARBONO', 'ANTE', 3, 1, 4100, 4500, 400, 'ACTIVO', 4),
(530, '6939684840241', 'JUEGO DE DESTORNILLADORES 4 PIEZAS', 'HYPER TOUGH', 1, 1, 4900, 5200, 300, 'ACTIVO', 4),
(531, '5605787406691', 'LLAVE SACA FILTRO ', 'ANTE', 1, 1, 5000, 5500, 500, 'ACTIVO', 4),
(532, '7808751405210', 'SILICONO DE AUTO', 'AUTO DRIVE', 1, 1, 2500, 2800, 300, 'ACTIVO', 4),
(533, '079567520054', 'WD 40', '', 2, 1, 4500, 4790, 290, 'ACTIVO', 4),
(534, '8433845683220', 'DISCO DEVASTE', 'BRICOTORI', 2, 1, 600, 950, 350, 'ACTIVO', 4),
(535, '9598888114243', 'RASTRILLO', 'HERRAN', 2, 1, 3500, 3800, 300, 'ACTIVO', 4),
(536, '3007', 'CHALECO REFLECTOR AMARILLO', '', 2, 1, 1500, 1900, 400, 'ACTIVO', 4),
(537, '3008', 'CINTA AISLADORA', '', 7, 2, 300, 450, 150, 'ACTIVO', 4),
(538, '5605787401337', 'EMBUDO FLEXIBLE ', 'ANTE', 3, 1, 2100, 2500, 400, 'ACTIVO', 4),
(539, '5605787016234', 'ALICATE HCS 10', 'ANTE', 1, 1, 6000, 6500, 500, 'ACTIVO', 4),
(540, '5606858205120', 'MANGO PARA DUCHA CROMADO', 'GIGASTAR', 1, 1, 6000, 6500, 500, 'ACTIVO', 4),
(541, '5605787016104', 'ALICATE HCS 8', 'ANTE', 1, 1, 3500, 3950, 450, 'ACTIVO', 4),
(542, '6939684800085', 'ALICATE UNIVERSAL ', 'HYPER TOUGH', 2, 1, 3500, 3900, 400, 'ACTIVO', 4),
(543, '606110955393', 'RODILLO 7P', 'ONCOLORS', 2, 1, 3500, 3900, 400, 'ACTIVO', 4);
INSERT INTO `producto` (`IdProducto`, `Codigo`, `Nombre`, `Descripcion`, `Stock`, `StockMin`, `PrecioCosto`, `PrecioVenta`, `Utilidad`, `Estado`, `IdCategoria`) VALUES
(544, '8433845631030', 'FIJADOR CABLE REDONDO 10MM', 'BRICOTORO', 1, 1, 1100, 1500, 400, 'ACTIVO', 4),
(545, '5605787043346', 'GRAPAS CON CLAVO 4MM', 'ANTE', 1, 1, 650, 900, 250, 'ACTIVO', 4),
(546, '7809839002093', 'ESPATULA DE 80MM', 'HELA', 1, 1, 2800, 3150, 350, 'ACTIVO', 4),
(547, '5605787033491', 'RODILLO DE PINTURA 120MM', 'ANTE', 2, 1, 2500, 2800, 300, 'ACTIVO', 4),
(548, '7800678601171', 'POMO DE TAPAS ', 'NICEIDEAL', 3, 1, 500, 750, 250, 'ACTIVO', 4),
(549, '3009', 'VARILLA DESTAPADORA DE CAÑERIA', '', 1, 1, 1500, 2000, 500, 'ACTIVO', 4),
(550, '6939684800108', 'ALICATE MEDIA CAÑA', 'HYPER TOUGH', 2, 1, 2900, 3200, 300, 'ACTIVO', 4),
(551, '5605787405687', 'PIE DE METRO', 'ANTE', 1, 1, 4000, 4300, 300, 'ACTIVO', 4),
(552, '5605787033552', 'RODILLO DE PINTURA 230MM', 'ANTE', 2, 1, 3800, 4100, 300, 'ACTIVO', 4),
(553, '7809828222501', 'RODILLO 18CM', 'IZCAL', 2, 1, 3300, 3650, 350, 'ACTIVO', 4),
(554, '7805208003777', 'PASTA MURO INTERIOR', 'TRICOLOR', 3, 1, 1100, 1400, 300, 'ACTIVO', 4),
(555, '5606858265100', 'CINTA ADHESIVA 30M', 'ANTE	', 2, 1, 1100, 1490, 390, 'ACTIVO', 4),
(556, '6939684820625', 'MARTILLO ', 'TYPER HOUGH', 2, 1, 5000, 5500, 500, 'ACTIVO', 4),
(557, '7808768200167', 'CINTA DE EMBALAJE 40X48', 'TESA', 1, 1, 3000, 3400, 400, 'ACTIVO', 4),
(558, '3010', 'PITA N6', '', 1, 1, 2100, 2500, 400, 'ACTIVO', 4),
(559, '3011', 'CINTA DE EMBALAJE CAFE', '', 1, 1, 1000, 1400, 400, 'ACTIVO', 4),
(560, '8434485001726', 'JUNTA DE PLASTICO ', '1MI STORE', 3, 1, 1500, 2000, 500, 'ACTIVO', 4),
(561, '2082001480584', 'GOMA DE LLAVE 3/4 AGUA FRIA', 'GRIFESA ', 7, 5, 200, 240, 40, 'ACTIVO', 4),
(562, '2082001480522', 'GOMA DE LLAVE 3/4 AGUA CALIENTE', 'GRIFIESA', 7, 5, 200, 240, 40, 'ACTIVO', 4),
(563, '2082001480508', 'GOMA DE LLAVE 1/2 AGUA FRIA', 'GRIFESA', 3, 5, 180, 220, 40, 'ACTIVO', 4),
(564, '2082001480133', 'GOMA DE LLAVE 1/2 AGUA CALIENTE', 'GRIFESA', 3, 5, 180, 220, 40, 'ACTIVO', 4),
(565, '5606858265681', 'COLA DE PVC', 'ANTE', 1, 1, 1000, 1400, 400, 'ACTIVO', 4),
(566, '7805502102695', 'ADHESIVO PVC 25CC', 'VINILIT', 2, 1, 700, 850, 150, 'ACTIVO', 4),
(567, '7805502013335', 'ADHESIVO PVC TRADICONAL 60CC', 'VINILIT', 2, 1, 1000, 1300, 300, 'ACTIVO', 4),
(568, '3012', 'PEGAMENTO LIQUIDO 3G', 'SUPER GLUE', 5, 2, 400, 500, 100, 'ACTIVO', 4),
(569, '3013', 'BROCHA 63MM', 'ONCOLORS', 1, 1, 1000, 1200, 200, 'ACTIVO', 4),
(570, '3015', 'BROCHA 38MM', 'ONCOLORS', 2, 1, 500, 700, 200, 'ACTIVO', 4),
(571, '3014', 'BROCHA 19MM', 'ONCOLORS', 2, 1, 350, 500, 150, 'ACTIVO', 4),
(572, '5606858240350', 'ESCARPIA ROSCADA 10PCS', 'ANTE', 1, 1, 800, 900, 100, 'ACTIVO', 4),
(573, '7730716024894', 'ADHESIVO INSTANTANEO', 'LA GOTITA', 1, 1, 3000, 3400, 400, 'ACTIVO', 4),
(574, '8433845671234', 'GRIFO ESFERA 3/4', 'BRICOTORO', 1, 1, 3500, 4100, 600, 'ACTIVO', 4),
(575, '5605787032975', 'GUANTES DE TRABAJO  NARANJO T10', 'ANTE', 2, 1, 1100, 1590, 490, 'ACTIVO', 4),
(576, '5605787033095', 'GUANTES DE TRABAJO T9', 'ANTE', 3, 1, 1100, 1590, 490, 'ACTIVO', 4),
(577, '3015', 'ABRAZADERA 44MM', 'ANTE', 1, 1, 450, 600, 150, 'ACTIVO', 4),
(578, '3016', 'ABRAZADERA 25MM', 'ANTE', 2, 1, 200, 300, 100, 'ACTIVO', 4),
(579, '3017', 'ABRAZADERA 38MM', 'ANTE', 2, 1, 450, 550, 100, 'ACTIVO', 4),
(580, '3018', 'ABRAZADERA 32MM', 'ANTE', 2, 1, 350, 450, 100, 'ACTIVO', 4),
(581, '3019', 'ABRAZADERA 27 MM', 'ANTE', 1, 1, 320, 400, 80, 'ACTIVO', 4),
(582, '3020', 'ABRAZADERA 25MM', 'ANTE', 1, 1, 200, 300, 100, 'ACTIVO', 4),
(583, '3021', 'ABRAZADERA 19MM', 'ANTE', 1, 1, 150, 200, 50, 'ACTIVO', 4),
(584, '3022', 'ABRAZADERA 16MM', 'ANTE', 1, 1, 150, 200, 50, 'ACTIVO', 4),
(585, '7806179201285', 'COPLA PVC 20MM', '', 4, 2, 500, 650, 150, 'ACTIVO', 4),
(586, '7806179603560', 'VALVULA BOLA COMPACTA 25X20MM', '', 5, 2, 6000, 6500, 500, 'ACTIVO', 4),
(587, '768120290366', 'ADAPTADOR 3/4', '', 2, 1, 1000, 1200, 200, 'ACTIVO', 4),
(588, '768120290359', 'ADAPTADOR 1/2', '', 2, 1, 900, 1050, 150, 'ACTIVO', 4),
(589, '7806179201322', 'CODO 20MM', '', 5, 2, 400, 550, 150, 'ACTIVO', 4),
(590, '3023', 'TEE PVC 25MM', '', 10, 3, 750, 850, 100, 'ACTIVO', 4),
(591, '7802955009922', 'LECHE CON CHOCOLATE MOCACCINO IND-', 'DANONE', 5, 3, 330, 400, 70, 'ACTIVO', 1),
(592, '7805000313777', 'OMO LAVADO A MANO 400 GRS.', 'OMO', 5, 2, 790, 1090, 300, 'ACTIVO', 2),
(593, '7804920280343', 'SUAVISANTE PARA ROPA COLOR PLUS', 'FUZOL   LA BODEGUITA', 2, 1, 1000, 1390, 390, 'ACTIVO', 2),
(594, '7804673690031', 'BOLSA  PARA BASURA  GRANDE 80 X 110 ', 'GLITT  LA BODEGUITA', 6, 2, 950, 1390, 440, 'ACTIVO', 2),
(595, '614143695254', 'PERROS PARA ROPA 24 UNIDADES', 'BRIGHT  LA BODEGUITA', 4, 2, 450, 900, 450, 'ACTIVO', 2),
(596, '7805000180874', 'PASTA DE DIENTES WHITENNING  90 GRS', 'PEPSODENT', 6, 2, 770, 1590, 820, 'ACTIVO', 2),
(597, '2010', 'ESCOBILLON  COLORES', ' LA BODEGUITA', 6, 2, 850, 1500, 650, 'ACTIVO', 2),
(598, '2011', 'LIQUIDO PARA LIMPIAR PISOS BIDON 5 LITROS', 'LA BODEGUITA', 4, 2, 1000, 1990, 990, 'ACTIVO', 2),
(599, '945127015', 'LIMPIAVIDRIOS  GATILLO 500 ML', 'MASTER  LA BODEGUITA', 2, 1, 990, 1540, 550, 'ACTIVO', 2),
(600, '945127035', 'SILICONA MULTIUSO PARA AUTOS 500 ML', 'MASTER  LA BODEGUITA', 2, 1, 1350, 2190, 840, 'ACTIVO', 2),
(601, '945127014', 'RENOVADOR DE GOMAS GATILLO 500 ML', 'MASTER  LA BOGUETITA', 2, 1, 1100, 1890, 790, 'ACTIVO', 2),
(602, '3024', 'BOLSA ALMACIGO 25 X 25', 'NEGRAS', 34, 10, 71, 100, 29, 'ACTIVO', 4),
(603, '3025', 'BOLSA ALMACIGO 20X20', '', 118, 20, 20, 40, 20, 'ACTIVO', 4),
(604, '3025', 'CORDEL TRENZADO 8 MM', '', 40, 10, 350, 490, 140, 'ACTIVO', 4),
(605, '3026', 'OVILLO  PITA COLOR 200 GRS', '', 4, 1, 1250, 1790, 540, 'ACTIVO', 4),
(606, '3027', 'SALITRE GRANEL', '', 25, 10, 692, 990, 298, 'ACTIVO', 4),
(607, '3028', 'UREA GRANEL', '', 25, 5, 476, 850, 374, 'ACTIVO', 4),
(608, '3029', 'GUANTE DE HILO AMARILLO', '', 6, 2, 700, 850, 150, 'ACTIVO', 4),
(609, '3030', 'GUANTE DE HILO PLOMO', '', 5, 2, 1000, 1290, 290, 'ACTIVO', 4),
(610, '3031', 'GUANTE DE HILO', '', 5, 2, 1000, 1290, 290, 'ACTIVO', 4),
(611, '7806395000006', 'DESTAPADOR Y LIMPIA CAÑAREIA', 'DESBLOCK (PASSOL)', 1, 1, 4000, 4400, 400, 'ACTIVO', 4),
(612, '7807354000914', 'CAJA DE DISTRIBUCION', '', 3, 1, 400, 500, 100, 'ACTIVO', 4),
(613, '7806179206457', 'SIFON LAVAPLATOS ', 'HOFFENS', 1, 1, 3900, 4250, 350, 'ACTIVO', 4),
(614, '7807354002611', 'INTERRUPTOR 9/12', 'ARIS', 1, 1, 1100, 1500, 400, 'ACTIVO', 4),
(615, '7807354005025', 'TOMA CTE DOBLE 10A', 'ARIS', 1, 1, 2100, 2500, 400, 'ACTIVO', 4),
(616, '1254784563100', 'DUCHA ELECTRICA', 'SUPER DUCHA CALOR', 1, 1, 12500, 15500, 3000, 'ACTIVO', 4),
(617, '7805070002816', 'SILICONA PARA AUTO', 'CAR CARE 3M', 1, 1, 2150, 2690, 540, 'ACTIVO', 4),
(618, '7805070002830', 'RENOVADOR PARA AUTO', 'CAR CARE 3M', 1, 1, 2150, 2690, 540, 'ACTIVO', 4),
(619, '3032', 'LLAVE DE LAVAPLATOS', '', 1, 1, 15000, 16500, 1500, 'ACTIVO', 4),
(620, '3033', 'FLEXIBLE PARA AGUA', '', 3, 1, 2400, 2800, 400, 'ACTIVO', 4),
(621, '3034', 'TRAMPA PARA RATONES GRANDE', '', 1, 1, 2100, 2500, 400, 'ACTIVO', 4),
(622, '3035', 'TRAMPA PARA RATONES CHICA', '', 1, 1, 1100, 1500, 400, 'ACTIVO', 4),
(623, '7311518233587', 'SIERRA ', '', 2, 1, 1000, 1300, 300, 'ACTIVO', 4),
(624, '40971900240700', 'POMELE 3/8 PAR', '', 1, 1, 1000, 1500, 500, 'ACTIVO', 4),
(625, '3036', 'CODO HE 1/2 COBRE', '', 3, 1, 600, 800, 200, 'ACTIVO', 4),
(626, '3037', 'CODO 1/2', '', 3, 1, 500, 700, 200, 'ACTIVO', 4),
(627, '3038', 'POMELE 1/2', '', 1, 1, 1200, 1700, 500, 'ACTIVO', 4),
(628, '3039', 'AGUJA PARA SACO', '', 2, 1, 500, 1000, 500, 'ACTIVO', 4),
(629, '3040', 'TERMINAL HE', '', 3, 1, 500, 800, 300, 'ACTIVO', 4),
(630, '3041', 'TERMINAL HI ', '', 3, 1, 500, 800, 300, 'ACTIVO', 4),
(631, '3042', 'REDUCCION PVC', '', 4, 2, 150, 250, 100, 'ACTIVO', 4),
(632, '3043', 'CODO 3/4 PVC HI', '', 3, 1, 500, 700, 200, 'ACTIVO', 4),
(633, '3044', 'CODO PVC 1/2', '', 3, 1, 400, 600, 200, 'ACTIVO', 4),
(634, '3045', 'TEE 1/2 PVC', '', 3, 1, 300, 500, 200, 'ACTIVO', 4),
(635, '3046', 'BROCA 7.5', '', 1, 1, 900, 1200, 300, 'ACTIVO', 4),
(636, '3051', 'GUANTES CABRITILLA (BLANCOS)', '', 1, 1, 2900, 3300, 400, 'ACTIVO', 4),
(637, '3048', 'BROCA 6', '', 1, 1, 500, 800, 300, 'ACTIVO', 4),
(638, '3049', 'BROCA 4.5', '', 1, 1, 500, 700, 200, 'ACTIVO', 4),
(639, '720206474992', 'MANILLA PARA ESTANQUE SANITARIO', 'FONALOSA', 1, 1, 1900, 2200, 300, 'ACTIVO', 4),
(640, '7807999383526', 'SELLO ANTIFUGA WC', 'HUMBOLDT', 1, 1, 5500, 5800, 300, 'ACTIVO', 4),
(641, '3050', 'MECHA MULTIPLE', 'FAMECIL', 1, 1, 5500, 6000, 500, 'ACTIVO', 4),
(642, '3051', 'GUANTES CABRITILLA', '', 1, 1, 2900, 3300, 400, 'ACTIVO', 4),
(643, '3052', 'GRAMPAS 1 1/4 (X KILO)', '', 1, 1, 2000, 2400, 400, 'ACTIVO', 4),
(644, '3053', 'GRAMPAS 1 (XKILO)', '', 1, 1, 2000, 2400, 400, 'ACTIVO', 4),
(645, '3054', 'CLAVOS 2P', '', 1, 1, 2000, 2400, 400, 'ACTIVO', 4),
(646, '3055', 'GRAMPAS 3/4 (X KILO)', '', 1, 1, 2000, 2400, 400, 'ACTIVO', 4),
(647, '3056', 'CLAVOS 6P', '', 1, 1, 1300, 1600, 300, 'ACTIVO', 4),
(648, '3057', 'CLAVOS 5P', '', 1, 1, 1300, 1600, 300, 'ACTIVO', 4),
(649, '3058', 'CLAVOS 3P', '', 1, 1, 1100, 1400, 300, 'ACTIVO', 4),
(650, '3059', 'CLAVOS 2 1/2', '', 1, 1, 2000, 2400, 400, 'ACTIVO', 4),
(651, '3060', 'ABRAZADERA', '', 6, 1, 300, 500, 200, 'ACTIVO', 4),
(652, '3061', 'GOMA DE LLAVE AGUA CALIENTE 1/2', '', 50, 6, 50, 100, 50, 'ACTIVO', 4),
(653, '7807371014741', 'CAJA DE DISTRIBUCION PVC', 'LEXO ELECTRIC', 1, 1, 990, 1200, 210, 'ACTIVO', 4),
(654, '7807354005032', 'TOMA CORRIENTE TRIPLE', 'ARIS', 1, 1, 2400, 2800, 400, 'ACTIVO', 4),
(655, '7805507000101', 'CINTA AUTOADHESIVA PARA JUNTAS ', 'JUNTA PLAC', 1, 1, 1000, 1500, 500, 'ACTIVO', 4),
(656, '720206472264', 'SET DE ANCLAJE PARA WC', 'FONALOZA', 1, 1, 1100, 1500, 400, 'ACTIVO', 4),
(657, '2881680010224', 'PORTALAMPARAS BASE RECTA', 'STANDFORD', 1, 1, 990, 1200, 210, 'ACTIVO', 4),
(658, '7613023673071', 'CLAVOS PARA CEMENTO (72MM-2 7/8)', '', 100, 20, 50, 100, 50, 'ACTIVO', 4),
(659, '20100191?sdm0096', 'CLAVOS PARA CEMENTO 1.1/2', 'TITAN', 100, 20, 50, 100, 50, 'ACTIVO', 4),
(660, '1000050042035', 'LAVE DE AGUA JARDIN (AZUL)', '', 1, 1, 2500, 3000, 500, 'ACTIVO', 4),
(661, '3062', 'LLAVE DE AGUA BOLA  (ROJA)', '', 1, 1, 2500, 3000, 500, 'ACTIVO', 4),
(662, '3063', 'LLAVE DE AGUA LAVAPLATOS', '', 1, 1, 4300, 4800, 500, 'ACTIVO', 4),
(663, '3064', 'CANDADO GRANDE', 'RESIS', 1, 1, 3500, 4000, 500, 'ACTIVO', 4),
(664, '3065', 'CANDADO MEDIANO', 'RESIS', 1, 1, 1500, 2000, 500, 'ACTIVO', 4),
(665, '3069', 'ENCHUFE MACHO NEGRO ', 'FME', 1, 1, 1000, 1500, 500, 'ACTIVO', 4),
(666, '3067', 'LAPIZ CARPINTERO', '', 2, 1, 300, 500, 200, 'ACTIVO', 4),
(667, '7897613325391', 'TERMINAL HI 1/2 PVC', '', 3, 1, 150, 300, 150, 'ACTIVO', 4),
(668, '3068', 'TRIPLE ', '', 1, 1, 1500, 2000, 500, 'ACTIVO', 4),
(669, '3069', 'ENCHUFE MACHO NEGRO ', 'FME', 1, 1, 1000, 1500, 500, 'ACTIVO', 4),
(670, '3070', 'SOQUETE AMPOLLETA', '', 1, 1, 300, 500, 200, 'ACTIVO', 4),
(671, '3071', 'INTERRUPTOR DE LAMPARA', '', 1, 1, 750, 1000, 250, 'ACTIVO', 4),
(672, '6109669104129', 'DISCO DE CORTE ', 'DELUN', 3, 1, 500, 700, 200, 'ACTIVO', 4),
(673, '3072', 'TAPA CIEGA', '', 4, 1, 150, 220, 70, 'ACTIVO', 4),
(674, '3073', 'UNION DOBLE', '', 3, 1, 150, 300, 150, 'ACTIVO', 4),
(675, '3074', 'UNION HE DE PLANZA', '', 3, 1, 150, 300, 150, 'ACTIVO', 4),
(676, '3075', 'ESCOBILLA DE ACERO ', '', 1, 1, 1500, 2000, 500, 'ACTIVO', 4),
(677, '3076', 'MANGUERA DE JARDIN', '20 METROS', 20, 5, 350, 400, 50, 'ACTIVO', 4),
(678, '3077', 'MANGUERA DE NIVEL', '15 METROS', 15, 5, 300, 500, 200, 'ACTIVO', 4),
(679, '3088', 'CORDEL NYLON', 'X METRO', 0, 0, 300, 400, 100, 'ACTIVO', 9),
(680, '6000', 'CADENA DE PASEO MASCOTA', '', 2, 1, 1500, 2500, 1000, 'ACTIVO', 9),
(681, '5606858205304', 'FLEXIBLE PARA LAVADORA', 'GIGASTAR', 1, 1, 2800, 3200, 400, 'ACTIVO', 4),
(682, '8434485048417', 'JUEGO DE TUBO DESAGUE', '1MI STOTRE', 1, 1, 2500, 2900, 400, 'ACTIVO', 4),
(683, '3078', 'RUEDA DE CARRETILLA', '', 1, 1, 5000, 5500, 500, 'ACTIVO', 4),
(684, '3079', 'CAMARA RUEDA DE CARRETILLA', '', 1, 1, 3500, 3800, 300, 'ACTIVO', 4),
(685, '3080', 'PINTURA BLANCA LATEX', 'CAMPU LATEX', 2, 1, 2000, 2650, 650, 'ACTIVO', 4),
(686, '3081', 'PINTURA CELESTE LATEX', 'CAMPU LATEX', 2, 1, 2000, 2650, 650, 'ACTIVO', 4),
(687, '3082', 'LIJA DE METAL', '', 24, 5, 400, 500, 100, 'ACTIVO', 4),
(688, '3083', 'LIJA DE MADERA', '', 29, 5, 150, 300, 150, 'ACTIVO', 4),
(689, '3084', 'LIJA AL AGUA', '', 5, 3, 300, 500, 200, 'ACTIVO', 4),
(690, '3085', 'YESO', '', 5, 1, 0, 0, 0, 'ACTIVO', 4),
(691, '7804630750457', 'AGUA REFRIGERANTE ', 'AUTO DRIVE', 2, 1, 2100, 2600, 500, 'ACTIVO', 4),
(692, '3086', 'PLATO DE PERRO DOBLE (BLANCO)', '', 1, 1, 1900, 2300, 400, 'ACTIVO', 9),
(693, '3087', 'PLATO DE PERRO DOBLE VERDE', '', 1, 1, 1500, 1900, 400, 'ACTIVO', 9),
(694, '3088', 'CORDEN NYLON', '', 0, 0, 300, 400, 100, 'ACTIVO', 4),
(695, '3089', 'CORDEL HILO', '', 0, 0, 300, 450, 150, 'ACTIVO', 4),
(696, '7802000002991', 'CHEETOS 22G', 'EVERCRISP', 9, 4, 200, 250, 50, 'ACTIVO', 1),
(697, '7802225683234', 'SELZ SABOR QUESO', 'ARCOR', 4, 2, 200, 250, 50, 'ACTIVO', 1),
(698, '7802225683050', 'SELZ CLASICAS', 'ARCOR', 4, 2, 200, 250, 50, 'ACTIVO', 1),
(699, '7802420125423', 'MANI SALADO', 'MARCO POLO', 2, 1, 900, 1100, 200, 'ACTIVO', 1),
(700, '7801552005580', 'RAMITAS', 'TIM', 9, 4, 200, 250, 50, 'ACTIVO', 1),
(701, '7802225682930', 'SELZ JAMON', 'ARCOR', 5, 3, 200, 250, 50, 'ACTIVO', 1),
(702, '7802000002526', 'PAPAS FRITAS CORTE AMERICANO', 'LAYS', 10, 3, 530, 650, 120, 'ACTIVO', 1),
(703, '7802000014062', 'PAPAS FRITAS TARRO', 'LAYS', 2, 1, 1100, 1590, 490, 'ACTIVO', 1),
(704, '7802800535569', 'PAPAS FRITAS TARRO 37G', 'KRYSPO', 3, 1, 450, 600, 150, 'ACTIVO', 1),
(705, '7802420127649', 'RAMITAS 270G', 'LIDER', 1, 1, 1000, 1300, 300, 'ACTIVO', 1),
(706, '7802095185104', 'PANCHITOS 180G', 'PANCHO VILLA', 2, 1, 1050, 1400, 350, 'ACTIVO', 1),
(707, '7802000015328', 'CHIS POP', 'EVERCRISP', 1, 1, 1100, 1600, 500, 'ACTIVO', 1),
(708, '7802000015335', 'CHEESE', 'EVERCRISP', 1, 1, 1100, 1600, 500, 'ACTIVO', 1),
(709, '7802000011108', 'PAPAS FRITAS CORTE LISO  230G', 'LAYS', 2, 1, 1400, 1700, 300, 'ACTIVO', 1),
(710, '7802420003929', 'PAPAS FRITAS CASERAS 250G', 'MARCO POLO', 2, 1, 1300, 1600, 300, 'ACTIVO', 1),
(711, '7802000014703', 'PAPAS FRITAS CORTE AMERICANO 380G', 'LAYS', 4, 2, 1750, 2050, 300, 'ACTIVO', 1),
(712, '7801000231882', 'ALCOHOL DESNATURALIZADO ', 'DIFEM PHARMA', 2, 1, 1350, 1600, 250, 'ACTIVO', 5),
(713, '7801403197914', 'ALCOHOL GEL 125ML', 'HEROME', 4, 1, 1450, 1990, 540, 'ACTIVO', 5),
(714, '7804945011441', 'ALCOHOL GEL', 'SIMONDS', 4, 1, 1050, 1400, 350, 'ACTIVO', 5),
(715, '7804907957725', 'ALCOHOL GEL 90ML', 'PIELARMINA CLINICAL ', 2, 1, 1000, 1300, 300, 'ACTIVO', 5),
(716, '8692641007214', 'PAÑUELOS DESECHABLES', 'TANGO', 10, 3, 170, 250, 80, 'ACTIVO', 5),
(717, '7802000013690', 'PAPAS FRITAS CASERAS 250G', 'MOM', 1, 1, 1450, 1650, 200, 'ACTIVO', 1),
(718, '7802900149017', 'QUESILLO ZERO LACTOSA ', 'SOPROLE', 1, 1, 1390, 1690, 300, 'ACTIVO', 11),
(719, '4019', 'MASCARILLAS X UNIDAD', '', 62, 15, 50, 100, 50, 'ACTIVO', 5),
(720, '7802950003550', 'COLADOS (PICADOS) POLLO-VERDURAS 9M', 'NESTLE ', 2, 1, 1100, 1590, 490, 'ACTIVO', 11),
(721, '7613030264705', 'COLADO (PICADO)CAZUELA DE POLLO 12M', 'NESTLE', 1, 1, 1590, 1990, 400, 'ACTIVO', 11),
(722, '7613030692669', 'COLADO (PICADO) CAZUELA DE VACUNO 12M', 'NESTLE			', 1, 1, 1590, 1990, 400, 'ACTIVO', 11),
(723, '7802950003543', 'COLADO (PICADO) CARNE FIDEOS 9M', 'NESTLE', 1, 1, 1100, 1590, 490, 'ACTIVO', 11),
(724, '7802950003543', 'COLADO( PICADO) CARNE FIDEOS  9M', 'NESTLE', 1, 1, 1190, 1590, 400, 'ACTIVO', 11),
(725, '7613030692577', 'COLADO (PICADO) CHARQUICAN 12M', 'NESTLE', 1, 1, 1550, 1990, 440, 'ACTIVO', 11),
(726, '7802930001453', 'QUESO CHANCO  9L', 'QUILLAYES', 2, 1, 1250, 1550, 300, 'ACTIVO', 11),
(727, '7802930001323', 'QUESO GAUDA 9L', 'QUILLAYES', 2, 1, 1250, 1550, 300, 'ACTIVO', 11),
(728, '7804613390205', 'QUESO LAMINADO GAUDA 9L', 'LIDER', 1, 1, 1250, 1550, 300, 'ACTIVO', 11),
(729, '7802900165000', 'QUESILLO ', 'SOPROLE ', 2, 1, 1390, 1690, 300, 'ACTIVO', 11),
(730, '7802900149017', 'QUESILLO ZERO LACTOSA ', 'SOPROLE', 1, 1, 1290, 1590, 300, 'ACTIVO', 11),
(731, '7804646540189', 'QUESO CREMA ', 'REFULCO', 3, 1, 1300, 1600, 300, 'ACTIVO', 11),
(732, '7802920009469', 'QUESO CHACRA', 'RIO BUENO', 0, 1, 1390, 1790, 400, 'ACTIVO', 11),
(733, '7802900097011', 'CREMA CHANTILLY', 'SOPROLE', 2, 1, 0, 0, 0, 'ACTIVO', 11),
(734, '7804634400259', 'QUESO GAUDA ', 'RUMAY', 2, 1, 6500, 7200, 700, 'ACTIVO', 11),
(735, '7808709500837', 'LECHE DE VAINILLA (BOMBILLA)', 'SURLAT', 6, 2, 230, 300, 70, 'ACTIVO', 11),
(736, '7804617470262', 'LECHE DE FRUTILLA (BOMBILLA)', 'SURLAT', 3, 2, 230, 300, 70, 'ACTIVO', 11),
(737, '7802900056025', 'LECHE DE FRUTILLA (BOMBILLA)', 'SOPROLE', 5, 2, 280, 350, 70, 'ACTIVO', 11),
(738, '7802950005264', 'LECHE CHOCOLATE (BOMBILLA)', 'NESQUIK', 3, 2, 310, 400, 90, 'ACTIVO', 11),
(739, '7802820700091', 'JUGO DE NARANJA (BOMBILLA)', 'ANDINA DEL VALLE', 4, 2, 240, 300, 60, 'ACTIVO', 11),
(740, '7802820700282', 'JUGO DE PIÑA (BOMBILLA)', 'ANDINA DEL VALLE', 4, 2, 240, 300, 60, 'ACTIVO', 11),
(741, '7802820700473', 'JUGO DE MANZANA (BOMBILLA)', 'ANDINA DEL VALLE', 5, 2, 240, 300, 60, 'ACTIVO', 11),
(742, '7802820550504', 'JUGO DE DURAZNO (BOMBILLA)', 'ANDINA DEL VALLE', 5, 2, 240, 300, 60, 'ACTIVO', 11),
(743, '7802900295035', 'JAELA DE PIÑA', 'SOPROLE', 3, 2, 180, 230, 50, 'ACTIVO', 11),
(744, '78014053', 'JALEA DE PIÑA', 'SOPROLE', 2, 2, 180, 230, 50, 'ACTIVO', 11),
(745, '78013292', 'JALEA DE NARANJA ', 'COLUN', 4, 2, 180, 230, 50, 'ACTIVO', 11),
(746, '78014053', 'JALEA DE PIÑA', 'COLUN', 2, 2, 180, 230, 50, 'ACTIVO', 11),
(747, '7613035953949', 'SEMOLA', 'NESTLE', 1, 1, 500, 650, 150, 'ACTIVO', 11),
(748, '7613035953741', 'SEMOLA CON SALSA DE FRAMBUEZA', 'NESTLE', 1, 1, 500, 650, 150, 'ACTIVO', 11),
(749, '7802955009922', 'MOCACCINO', 'DANODE', 4, 2, 300, 400, 100, 'ACTIVO', 11),
(750, '7802900230241', 'YOGURTH DAMASCO', 'SOPROLE', 8, 3, 170, 250, 80, 'ACTIVO', 11),
(751, '7802900230227', 'YOGURTH FRUTILLA', 'SOPROLE', 9, 3, 170, 250, 80, 'ACTIVO', 11),
(752, '7802900230289', 'YOGURTH FRAMBUEZA', 'SOPROLE', 3, 3, 170, 250, 80, 'ACTIVO', 11),
(753, '7802900230296', 'YOGURTH DE PLATANO', 'SOPROLE', 12, 3, 170, 250, 80, 'ACTIVO', 11),
(754, '7802900001414', 'YOGURTH PROTEIN NATURAL', 'SOPROLE', 4, 2, 380, 450, 70, 'ACTIVO', 11),
(755, '7613036927857', 'LECHE MOKACCINO', 'NESCAFE', 2, 1, 0, 0, 0, 'ACTIVO', 11),
(756, '7613036927420', 'LECHE DE CAPPUCINO', 'NESCAFE', 1, 1, 0, 0, 0, 'ACTIVO', 11),
(757, '7802900001407', 'YOGURTH PROTEIN FRUTILLA', 'SOPROLE', 2, 1, 350, 450, 100, 'ACTIVO', 11),
(758, '7802900235253', 'YOGURTH ZERO LACTO VAINILLA', 'SOPROLE', 4, 2, 290, 350, 60, 'ACTIVO', 11),
(759, '7802900002138', 'YOGURTH SIN AZUCAR FRUTOS SECOS', 'SOPROLE', 2, 2, 310, 400, 90, 'ACTIVO', 11),
(760, '7802900230258', 'YOGURTH VAINILLA ', 'SOPROLE', 4, 3, 180, 250, 70, 'ACTIVO', 11),
(761, '7802955001087', 'YOGURTH FRUTILLA', 'CALAN', 2, 2, 140, 200, 60, 'ACTIVO', 11),
(762, '7802955001063', 'YOGURTH DAMASCO ', 'CALAN', 3, 2, 140, 200, 60, 'ACTIVO', 11),
(763, '7802955000998', 'YOGURTH CHIRIMOYA', 'CALAN', 4, 2, 140, 200, 60, 'ACTIVO', 11),
(764, '7802810006851', 'MANTECA 200G', 'PALMIN', 1, 1, 820, 1050, 230, 'ACTIVO', 11),
(765, '7802810022158', 'MANTECA 200G', 'CRUCINA', 2, 1, 980, 1200, 220, 'ACTIVO', 11),
(766, '7893000047297', 'MARGARINA  500G (POTE)', 'DELINE', 3, 1, 750, 950, 200, 'ACTIVO', 11),
(767, '7893000065253', 'MARGARINA CREMOSA', 'QUALY SADIA ', 2, 1, 1290, 1490, 200, 'ACTIVO', 11),
(768, '7804646540011', 'MANTEQUILLA DE CAMPO 250G', 'REFULCO', 2, 1, 1800, 2100, 300, 'ACTIVO', 11),
(769, '7802900121013', 'MANTEQUILLA CON SAL 250G', 'SOPROLE', 1, 2, 1850, 2150, 300, 'ACTIVO', 11),
(770, '7802810021120', 'MARGARINA ', 'CALO', 3, 2, 320, 450, 130, 'ACTIVO', 11),
(771, '7802810006868', 'MANTECA 100G', 'PALMIN', 9, 3, 450, 650, 200, 'ACTIVO', 11),
(772, '7801930017976', 'SALAME 100G', 'PF', 3, 2, 1100, 1400, 300, 'ACTIVO', 11),
(773, '7801907000093', 'MORTADELA LISA 150G', 'SAN JORGE ', 4, 2, 720, 850, 130, 'ACTIVO', 11),
(774, '7801927000349', 'JAMON SANDWICH', 'SOLER', 2, 1, 0, 0, 0, 'ACTIVO', 11),
(775, '7801900082065', 'JAMON ACARAMELADO', 'WINTER', 2, 2, 0, 0, 0, 'ACTIVO', 11),
(776, '7801930005782', 'JAMON PIERNA', 'PF', 2, 1, 0, 0, 0, 'ACTIVO', 11);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE `proveedor` (
  `IdProveedor` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Rol` varchar(12) DEFAULT NULL,
  `Rut` varchar(12) DEFAULT NULL,
  `Direccion` varchar(100) DEFAULT NULL,
  `Telefono` varchar(10) DEFAULT NULL,
  `Celular` varchar(15) DEFAULT NULL,
  `Email` varchar(80) DEFAULT NULL,
  `Cuenta1` varchar(50) DEFAULT NULL,
  `Cuenta2` varchar(50) DEFAULT NULL,
  `Estado` varchar(30) NOT NULL,
  `Obsv` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipodocumento`
--

DROP TABLE IF EXISTS `tipodocumento`;
CREATE TABLE `tipodocumento` (
  `IdTipoDocumento` int(11) NOT NULL,
  `Descripcion` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipodocumento`
--

INSERT INTO `tipodocumento` (`IdTipoDocumento`, `Descripcion`) VALUES
(1, 'BOLETA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

DROP TABLE IF EXISTS `tipousuario`;
CREATE TABLE `tipousuario` (
  `IdTipoUsuario` int(11) NOT NULL,
  `Descripcion` varchar(20) NOT NULL,
  `p_venta` int(11) DEFAULT NULL,
  `p_compra` int(11) DEFAULT NULL,
  `p_producto` int(11) DEFAULT NULL,
  `p_proveedor` int(11) DEFAULT NULL,
  `p_empleado` int(11) DEFAULT NULL,
  `p_cliente` int(11) DEFAULT NULL,
  `p_categoria` int(11) DEFAULT NULL,
  `p_tipodoc` int(11) DEFAULT NULL,
  `p_tipouser` int(11) DEFAULT NULL,
  `p_anularv` int(11) DEFAULT NULL,
  `p_anularc` int(11) DEFAULT NULL,
  `p_estadoprod` int(11) DEFAULT NULL,
  `p_ventare` int(11) DEFAULT NULL,
  `p_ventade` int(11) DEFAULT NULL,
  `p_estadistica` int(11) DEFAULT NULL,
  `p_comprare` int(11) DEFAULT NULL,
  `p_comprade` int(11) DEFAULT NULL,
  `p_pass` int(11) DEFAULT NULL,
  `p_respaldar` int(11) DEFAULT NULL,
  `p_restaurar` int(11) DEFAULT NULL,
  `p_caja` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`IdTipoUsuario`, `Descripcion`, `p_venta`, `p_compra`, `p_producto`, `p_proveedor`, `p_empleado`, `p_cliente`, `p_categoria`, `p_tipodoc`, `p_tipouser`, `p_anularv`, `p_anularc`, `p_estadoprod`, `p_ventare`, `p_ventade`, `p_estadistica`, `p_comprare`, `p_comprade`, `p_pass`, `p_respaldar`, `p_restaurar`, `p_caja`) VALUES
(2, 'Administrador', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

DROP TABLE IF EXISTS `venta`;
CREATE TABLE `venta` (
  `IdVenta` int(11) NOT NULL,
  `IdTipoDocumento` int(11) NOT NULL,
  `IdCliente` int(11) NOT NULL,
  `IdEmpleado` int(11) NOT NULL,
  `Serie` varchar(5) DEFAULT NULL,
  `Numero` varchar(20) DEFAULT NULL,
  `Fecha` date NOT NULL,
  `TotalVenta` int(11) NOT NULL,
  `Descuento` int(11) NOT NULL,
  `SubTotal` int(11) NOT NULL,
  `Iva` int(11) NOT NULL,
  `TotalPagar` int(11) NOT NULL,
  `Estado` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`IdCategoria`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`IdCliente`);

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`IdCompra`),
  ADD KEY `fk_Compra_Proveedor1_idx` (`IdProveedor`),
  ADD KEY `fk_Compra_Empleado1_idx` (`IdEmpleado`),
  ADD KEY `fk_Compra_TipoDocumento1_idx` (`IdTipoDocumento`);

--
-- Indices de la tabla `detallecompra`
--
ALTER TABLE `detallecompra`
  ADD KEY `fk_DetalleCompra_Compra1_idx` (`IdCompra`),
  ADD KEY `fk_DetalleCompra_Producto1_idx` (`IdProducto`);

--
-- Indices de la tabla `detalleventa`
--
ALTER TABLE `detalleventa`
  ADD KEY `fk_DetalleVenta_Producto1_idx` (`IdProducto`),
  ADD KEY `fk_DetalleVenta_Venta1_idx` (`IdVenta`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`IdEmpleado`),
  ADD KEY `fk_Empleado_TipoUsuario1_idx` (`IdTipoUsuario`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`IdProducto`),
  ADD KEY `fk_Producto_Categoria_idx` (`IdCategoria`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`IdProveedor`);

--
-- Indices de la tabla `tipodocumento`
--
ALTER TABLE `tipodocumento`
  ADD PRIMARY KEY (`IdTipoDocumento`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`IdTipoUsuario`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`IdVenta`),
  ADD KEY `fk_Venta_TipoDocumento1_idx` (`IdTipoDocumento`),
  ADD KEY `fk_Venta_Cliente1_idx` (`IdCliente`),
  ADD KEY `fk_Venta_Empleado1_idx` (`IdEmpleado`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `IdCategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `IdCliente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `IdCompra` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `IdEmpleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `IdProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=777;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `IdProveedor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipodocumento`
--
ALTER TABLE `tipodocumento`
  MODIFY `IdTipoDocumento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `IdTipoUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `IdVenta` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `fk_Compra_Empleado1` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Compra_Proveedor1` FOREIGN KEY (`IdProveedor`) REFERENCES `proveedor` (`IdProveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Compra_TipoDocumento1` FOREIGN KEY (`IdTipoDocumento`) REFERENCES `tipodocumento` (`IdTipoDocumento`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `detallecompra`
--
ALTER TABLE `detallecompra`
  ADD CONSTRAINT `fk_DetalleCompra_Compra1` FOREIGN KEY (`IdCompra`) REFERENCES `compra` (`IdCompra`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_DetalleCompra_Producto1` FOREIGN KEY (`IdProducto`) REFERENCES `producto` (`IdProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `detalleventa`
--
ALTER TABLE `detalleventa`
  ADD CONSTRAINT `fk_DetalleVenta_Producto1` FOREIGN KEY (`IdProducto`) REFERENCES `producto` (`IdProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_DetalleVenta_Venta1` FOREIGN KEY (`IdVenta`) REFERENCES `venta` (`IdVenta`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `fk_Empleado_TipoUsuario1` FOREIGN KEY (`IdTipoUsuario`) REFERENCES `tipousuario` (`IdTipoUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `fk_Producto_Categoria` FOREIGN KEY (`IdCategoria`) REFERENCES `categoria` (`IdCategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `fk_Venta_Cliente1` FOREIGN KEY (`IdCliente`) REFERENCES `cliente` (`IdCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Venta_Empleado1` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Venta_TipoDocumento1` FOREIGN KEY (`IdTipoDocumento`) REFERENCES `tipodocumento` (`IdTipoDocumento`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
