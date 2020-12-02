-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gesoc
-- ------------------------------------------------------
-- Server version	5.7.31-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bandejamensaje`
--

DROP TABLE IF EXISTS `bandejamensaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bandejamensaje` (
  `idbandejamensaje` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`idbandejamensaje`),
  KEY `FK_513ee834b8pi6ylixh6ucx8kx` (`idUsuario`),
  CONSTRAINT `FK_513ee834b8pi6ylixh6ucx8kx` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bandejamensaje`
--

LOCK TABLES `bandejamensaje` WRITE;
/*!40000 ALTER TABLE `bandejamensaje` DISABLE KEYS */;
INSERT INTO `bandejamensaje` VALUES (1,1);
/*!40000 ALTER TABLE `bandejamensaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoriaentidad`
--

DROP TABLE IF EXISTS `categoriaentidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoriaentidad` (
  `idcategoriaentidad` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idcategoriaentidad`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoriaentidad`
--

LOCK TABLES `categoriaentidad` WRITE;
/*!40000 ALTER TABLE `categoriaentidad` DISABLE KEYS */;
INSERT INTO `categoriaentidad` VALUES (1,'mediana'),(2,'grande');
/*!40000 ALTER TABLE `categoriaentidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoriapresupuesto`
--

DROP TABLE IF EXISTS `categoriapresupuesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoriapresupuesto` (
  `idCategoriaPresupuesto` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `idcriteriopresupuesto` int(11) DEFAULT NULL,
  `idEgreso` int(11) DEFAULT NULL,
  `idEntidadjuridica` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCategoriaPresupuesto`),
  KEY `FK_ncxn5fnfk2s08ovvpft0bbyil` (`idcriteriopresupuesto`),
  KEY `FK_al0fpbfvdb590i6a49mg9esd5` (`idEgreso`),
  KEY `FK_m3fefndsjlkuvhybolcj1mtg3` (`idEntidadjuridica`),
  CONSTRAINT `FK_al0fpbfvdb590i6a49mg9esd5` FOREIGN KEY (`idEgreso`) REFERENCES `egreso` (`idEgreso`),
  CONSTRAINT `FK_m3fefndsjlkuvhybolcj1mtg3` FOREIGN KEY (`idEntidadjuridica`) REFERENCES `entidadjuridica` (`idEntidadJuridica`),
  CONSTRAINT `FK_ncxn5fnfk2s08ovvpft0bbyil` FOREIGN KEY (`idcriteriopresupuesto`) REFERENCES `criteriopresupuesto` (`idcriteriopresupuesto`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoriapresupuesto`
--

LOCK TABLES `categoriapresupuesto` WRITE;
/*!40000 ALTER TABLE `categoriapresupuesto` DISABLE KEYS */;
INSERT INTO `categoriapresupuesto` VALUES (1,'nacional',1,1,1);
/*!40000 ALTER TABLE `categoriapresupuesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciudad` (
  `idCiudad` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `idPais` int(11) DEFAULT NULL,
  `idProvincia` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCiudad`),
  KEY `FK_gxq80hcp0v35nqa0h6bxvtbut` (`idPais`),
  KEY `FK_cx7gedw5snexx2pf54knwpb94` (`idProvincia`),
  CONSTRAINT `FK_cx7gedw5snexx2pf54knwpb94` FOREIGN KEY (`idProvincia`) REFERENCES `provincia` (`idProvincia`),
  CONSTRAINT `FK_gxq80hcp0v35nqa0h6bxvtbut` FOREIGN KEY (`idPais`) REFERENCES `pais` (`idPais`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (1,'BOB','bolivia',1,1);
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuracionentidadjuridica`
--

DROP TABLE IF EXISTS `configuracionentidadjuridica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `configuracionentidadjuridica` (
  `idConfig` int(11) NOT NULL AUTO_INCREMENT,
  `idEntidadjuridica` int(11) DEFAULT NULL,
  PRIMARY KEY (`idConfig`),
  KEY `FK_iuiix7inash4h86ucud1savey` (`idEntidadjuridica`),
  CONSTRAINT `FK_iuiix7inash4h86ucud1savey` FOREIGN KEY (`idEntidadjuridica`) REFERENCES `entidadjuridica` (`idEntidadJuridica`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracionentidadjuridica`
--

LOCK TABLES `configuracionentidadjuridica` WRITE;
/*!40000 ALTER TABLE `configuracionentidadjuridica` DISABLE KEYS */;
INSERT INTO `configuracionentidadjuridica` VALUES (1,1);
/*!40000 ALTER TABLE `configuracionentidadjuridica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `criteriopresupuesto`
--

DROP TABLE IF EXISTS `criteriopresupuesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `criteriopresupuesto` (
  `idcriteriopresupuesto` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `idEntidadJuridica` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcriteriopresupuesto`),
  KEY `FK_bmqtgowq6xnwfb55rjnpjf0de` (`parentId`),
  KEY `FK_p76uw19ijkqu3p46myweld1ip` (`idEntidadJuridica`),
  CONSTRAINT `FK_bmqtgowq6xnwfb55rjnpjf0de` FOREIGN KEY (`parentId`) REFERENCES `criteriopresupuesto` (`idcriteriopresupuesto`),
  CONSTRAINT `FK_p76uw19ijkqu3p46myweld1ip` FOREIGN KEY (`idEntidadJuridica`) REFERENCES `entidadjuridica` (`idEntidadJuridica`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criteriopresupuesto`
--

LOCK TABLES `criteriopresupuesto` WRITE;
/*!40000 ALTER TABLE `criteriopresupuesto` DISABLE KEYS */;
INSERT INTO `criteriopresupuesto` VALUES (1,'critpresupuesto',1,1);
/*!40000 ALTER TABLE `criteriopresupuesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccion` (
  `idDireccion` int(11) NOT NULL AUTO_INCREMENT,
  `altura` int(11) DEFAULT NULL,
  `calle` varchar(255) DEFAULT NULL,
  `codPostal` int(11) DEFAULT NULL,
  `depto` varchar(255) DEFAULT NULL,
  `piso` int(11) DEFAULT NULL,
  `idCiudad` int(11) DEFAULT NULL,
  `idEntidadJuridica` int(11) DEFAULT NULL,
  `idProveedor` int(11) DEFAULT NULL,
  PRIMARY KEY (`idDireccion`),
  KEY `FK_hhbm2qsr4gr675qjkj8sg99yw` (`idCiudad`),
  KEY `FK_26e9aafjo8l01e0plb6dsjwtm` (`idEntidadJuridica`),
  KEY `FK_caxfeufhy2eh12a9scktnqofi` (`idProveedor`),
  CONSTRAINT `FK_26e9aafjo8l01e0plb6dsjwtm` FOREIGN KEY (`idEntidadJuridica`) REFERENCES `entidadjuridica` (`idEntidadJuridica`),
  CONSTRAINT `FK_caxfeufhy2eh12a9scktnqofi` FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`idProveedor`),
  CONSTRAINT `FK_hhbm2qsr4gr675qjkj8sg99yw` FOREIGN KEY (`idCiudad`) REFERENCES `ciudad` (`idCiudad`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
INSERT INTO `direccion` VALUES (1,123,'falsa',123,'122',4,1,1,NULL);
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documentocomercial`
--

DROP TABLE IF EXISTS `documentocomercial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `documentocomercial` (
  `idDocumentoComercial` int(11) NOT NULL AUTO_INCREMENT,
  `numeroDocCom` int(11) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `idEgreso` int(11) DEFAULT NULL,
  `idPresupuesto` int(11) DEFAULT NULL,
  PRIMARY KEY (`idDocumentoComercial`),
  KEY `FK_5pbbsorvjgyha3faq90plkwnk` (`idEgreso`),
  KEY `FK_dpgcrq2a9w602dkmoibytvyek` (`idPresupuesto`),
  CONSTRAINT `FK_5pbbsorvjgyha3faq90plkwnk` FOREIGN KEY (`idEgreso`) REFERENCES `egreso` (`idEgreso`),
  CONSTRAINT `FK_dpgcrq2a9w602dkmoibytvyek` FOREIGN KEY (`idPresupuesto`) REFERENCES `presupuesto` (`idPresupuesto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentocomercial`
--

LOCK TABLES `documentocomercial` WRITE;
/*!40000 ALTER TABLE `documentocomercial` DISABLE KEYS */;
INSERT INTO `documentocomercial` VALUES (1,123123123,'/FS/Docs/','xls',1,NULL),(2,12311,'/FS/Presup/','pdf',1,NULL);
/*!40000 ALTER TABLE `documentocomercial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `egreso`
--

DROP TABLE IF EXISTS `egreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `egreso` (
  `idEgreso` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `fechaegreso` date DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `numeroinstrumentopago` bigint(20) DEFAULT NULL,
  `validado` bit(1) DEFAULT NULL,
  `idDocumentoComercial` int(11) DEFAULT NULL,
  `idEntidadJuridica` int(11) DEFAULT NULL,
  `idmediodepago` int(11) DEFAULT NULL,
  `idMoneda` int(11) DEFAULT NULL,
  `presupuestoElegido` int(11) DEFAULT NULL,
  `idProveedor` int(11) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEgreso`),
  KEY `FK_7svpity2riyi6bj11q73fbnao` (`idDocumentoComercial`),
  KEY `FK_lmcv855w7i0evvsvwl163idi1` (`idEntidadJuridica`),
  KEY `FK_1dslpj93dfyd5i30ipr7qn055` (`idmediodepago`),
  KEY `FK_61jp3vpw7p4rx28wvq0345mil` (`idMoneda`),
  KEY `FK_905gpv0v0su9k90fhmygcuegp` (`presupuestoElegido`),
  KEY `FK_7ede40mlpoy70dgwp5xa4mfwk` (`idProveedor`),
  KEY `FK_kkp3ye5p6457hh9jls91igupg` (`idUsuario`),
  CONSTRAINT `FK_1dslpj93dfyd5i30ipr7qn055` FOREIGN KEY (`idmediodepago`) REFERENCES `mediodepago` (`idmediodepago`),
  CONSTRAINT `FK_61jp3vpw7p4rx28wvq0345mil` FOREIGN KEY (`idMoneda`) REFERENCES `moneda` (`idMoneda`),
  CONSTRAINT `FK_7ede40mlpoy70dgwp5xa4mfwk` FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`idProveedor`),
  CONSTRAINT `FK_7svpity2riyi6bj11q73fbnao` FOREIGN KEY (`idDocumentoComercial`) REFERENCES `documentocomercial` (`idDocumentoComercial`),
  CONSTRAINT `FK_905gpv0v0su9k90fhmygcuegp` FOREIGN KEY (`presupuestoElegido`) REFERENCES `presupuesto` (`idPresupuesto`),
  CONSTRAINT `FK_kkp3ye5p6457hh9jls91igupg` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`),
  CONSTRAINT `FK_lmcv855w7i0evvsvwl163idi1` FOREIGN KEY (`idEntidadJuridica`) REFERENCES `entidadjuridica` (`idEntidadJuridica`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `egreso`
--

LOCK TABLES `egreso` WRITE;
/*!40000 ALTER TABLE `egreso` DISABLE KEYS */;
INSERT INTO `egreso` VALUES (1,'egreso1',NULL,123,1,_binary '',1,1,1,1,1,NULL,1);
/*!40000 ALTER TABLE `egreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `egresoxitem`
--

DROP TABLE IF EXISTS `egresoxitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `egresoxitem` (
  `idEgresoXItem` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `idEgreso` int(11) DEFAULT NULL,
  `idItem` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEgresoXItem`),
  KEY `FK_sxqsy1y0c32nmdbvel2u3wxaa` (`idEgreso`),
  KEY `FK_ne7mujh9r0jrb5f77vn14myuw` (`idItem`),
  CONSTRAINT `FK_ne7mujh9r0jrb5f77vn14myuw` FOREIGN KEY (`idItem`) REFERENCES `item` (`idItem`),
  CONSTRAINT `FK_sxqsy1y0c32nmdbvel2u3wxaa` FOREIGN KEY (`idEgreso`) REFERENCES `egreso` (`idEgreso`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `egresoxitem`
--

LOCK TABLES `egresoxitem` WRITE;
/*!40000 ALTER TABLE `egresoxitem` DISABLE KEYS */;
INSERT INTO `egresoxitem` VALUES (1,50,30,1,1);
/*!40000 ALTER TABLE `egresoxitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entidadbase`
--

DROP TABLE IF EXISTS `entidadbase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entidadbase` (
  `idEntidadBase` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `nombreficiticio` varchar(255) DEFAULT NULL,
  `idEntidadJuridica` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEntidadBase`),
  KEY `FK_3kla3enx1deym3qa1n0n1cqgv` (`idEntidadJuridica`),
  CONSTRAINT `FK_3kla3enx1deym3qa1n0n1cqgv` FOREIGN KEY (`idEntidadJuridica`) REFERENCES `entidadjuridica` (`idEntidadJuridica`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidadbase`
--

LOCK TABLES `entidadbase` WRITE;
/*!40000 ALTER TABLE `entidadbase` DISABLE KEYS */;
INSERT INTO `entidadbase` VALUES (1,'truchex',_binary '','trucho x',1);
/*!40000 ALTER TABLE `entidadbase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entidadjuridica`
--

DROP TABLE IF EXISTS `entidadjuridica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entidadjuridica` (
  `idEntidadJuridica` int(11) NOT NULL AUTO_INCREMENT,
  `actividad` varchar(255) DEFAULT NULL,
  `cantidadempleados` int(11) DEFAULT NULL,
  `codigoinscripcion` int(11) DEFAULT NULL,
  `cuit` int(11) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `nombreficticio` varchar(255) DEFAULT NULL,
  `promedioventas` double DEFAULT NULL,
  `idcategoriaentidad` int(11) DEFAULT NULL,
  `idConfiguracionEntidadJuridica` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEntidadJuridica`),
  KEY `FK_jckelgmbcbp2bt0e09ajwxuo0` (`idcategoriaentidad`),
  KEY `FK_7s4uxh17dkdvchf2dyb3l4lcu` (`idConfiguracionEntidadJuridica`),
  CONSTRAINT `FK_7s4uxh17dkdvchf2dyb3l4lcu` FOREIGN KEY (`idConfiguracionEntidadJuridica`) REFERENCES `configuracionentidadjuridica` (`idConfig`),
  CONSTRAINT `FK_jckelgmbcbp2bt0e09ajwxuo0` FOREIGN KEY (`idcategoriaentidad`) REFERENCES `categoriaentidad` (`idcategoriaentidad`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidadjuridica`
--

LOCK TABLES `entidadjuridica` WRITE;
/*!40000 ALTER TABLE `entidadjuridica` DISABLE KEYS */;
INSERT INTO `entidadjuridica` VALUES (1,'limpieza',13,123123123,131,_binary '','nombre',40,1,1);
/*!40000 ALTER TABLE `entidadjuridica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingreso`
--

DROP TABLE IF EXISTS `ingreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingreso` (
  `idIngreso` int(11) NOT NULL AUTO_INCREMENT,
  `clienteId` int(11) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fechaIngreso` date DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `idEgreso` int(11) DEFAULT NULL,
  `idEntidadJuridica` int(11) DEFAULT NULL,
  `idMoneda` int(11) DEFAULT NULL,
  PRIMARY KEY (`idIngreso`),
  KEY `FK_koc6c98j6bf7pf6ktj4gc1otr` (`idEgreso`),
  KEY `FK_asobsvl70umgh15bdf9gr6wos` (`idEntidadJuridica`),
  KEY `FK_qlnijb6w3ik2jducpfcmkt5cn` (`idMoneda`),
  CONSTRAINT `FK_asobsvl70umgh15bdf9gr6wos` FOREIGN KEY (`idEntidadJuridica`) REFERENCES `entidadjuridica` (`idEntidadJuridica`),
  CONSTRAINT `FK_koc6c98j6bf7pf6ktj4gc1otr` FOREIGN KEY (`idEgreso`) REFERENCES `egreso` (`idEgreso`),
  CONSTRAINT `FK_qlnijb6w3ik2jducpfcmkt5cn` FOREIGN KEY (`idMoneda`) REFERENCES `moneda` (`idMoneda`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingreso`
--

LOCK TABLES `ingreso` WRITE;
/*!40000 ALTER TABLE `ingreso` DISABLE KEYS */;
INSERT INTO `ingreso` VALUES (1,NULL,'subscription',NULL,1313,1,1,NULL);
/*!40000 ALTER TABLE `ingreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `idItem` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `productoServicio` varchar(255) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`idItem`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'cosa determinada',NULL,'cosa',13);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mediodepago`
--

DROP TABLE IF EXISTS `mediodepago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mediodepago` (
  `idmediodepago` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idmediodepago`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mediodepago`
--

LOCK TABLES `mediodepago` WRITE;
/*!40000 ALTER TABLE `mediodepago` DISABLE KEYS */;
INSERT INTO `mediodepago` VALUES (1,'tarjeta',NULL,'banco');
/*!40000 ALTER TABLE `mediodepago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensaje`
--

DROP TABLE IF EXISTS `mensaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensaje` (
  `idMensaje` int(11) NOT NULL AUTO_INCREMENT,
  `asunto` varchar(255) DEFAULT NULL,
  `contenido` varchar(255) DEFAULT NULL,
  `fechaCreacion` date DEFAULT NULL,
  `leido` bit(1) DEFAULT NULL,
  `idbandejamensaje` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMensaje`),
  KEY `FK_ng39lv9k2kn1h519tabrunoxi` (`idbandejamensaje`),
  CONSTRAINT `FK_ng39lv9k2kn1h519tabrunoxi` FOREIGN KEY (`idbandejamensaje`) REFERENCES `bandejamensaje` (`idbandejamensaje`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensaje`
--

LOCK TABLES `mensaje` WRITE;
/*!40000 ALTER TABLE `mensaje` DISABLE KEYS */;
INSERT INTO `mensaje` VALUES (1,'asunto','contenido de mail',NULL,NULL,1);
/*!40000 ALTER TABLE `mensaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moneda`
--

DROP TABLE IF EXISTS `moneda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moneda` (
  `idMoneda` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `simbolo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idMoneda`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moneda`
--

LOCK TABLES `moneda` WRITE;
/*!40000 ALTER TABLE `moneda` DISABLE KEYS */;
INSERT INTO `moneda` VALUES (1,'pesos','psarg','$');
/*!40000 ALTER TABLE `moneda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pais` (
  `idPais` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idPais`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,'1','bolivia');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permiso`
--

DROP TABLE IF EXISTS `permiso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permiso` (
  `idPermiso` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idPermiso`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permiso`
--

LOCK TABLES `permiso` WRITE;
/*!40000 ALTER TABLE `permiso` DISABLE KEYS */;
INSERT INTO `permiso` VALUES (1,'creador');
/*!40000 ALTER TABLE `permiso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presupuesto`
--

DROP TABLE IF EXISTS `presupuesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presupuesto` (
  `idPresupuesto` int(11) NOT NULL AUTO_INCREMENT,
  `detalles` varchar(255) DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `idCategoriaPresupuesto` int(11) DEFAULT NULL,
  `idDocumentoComercial` int(11) DEFAULT NULL,
  `idEgreso` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPresupuesto`),
  KEY `FK_cfs1phpt9trhof0aeg7469h4k` (`idCategoriaPresupuesto`),
  KEY `FK_fi6t5f6d7j511f6m94kbl4200` (`idDocumentoComercial`),
  KEY `FK_66j6h9i9em57tfjfwkatipgvh` (`idEgreso`),
  CONSTRAINT `FK_66j6h9i9em57tfjfwkatipgvh` FOREIGN KEY (`idEgreso`) REFERENCES `egreso` (`idEgreso`),
  CONSTRAINT `FK_cfs1phpt9trhof0aeg7469h4k` FOREIGN KEY (`idCategoriaPresupuesto`) REFERENCES `categoriapresupuesto` (`idCategoriaPresupuesto`),
  CONSTRAINT `FK_fi6t5f6d7j511f6m94kbl4200` FOREIGN KEY (`idDocumentoComercial`) REFERENCES `documentocomercial` (`idDocumentoComercial`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto`
--

LOCK TABLES `presupuesto` WRITE;
/*!40000 ALTER TABLE `presupuesto` DISABLE KEYS */;
INSERT INTO `presupuesto` VALUES (1,'licitacion',123123,1,2,1);
/*!40000 ALTER TABLE `presupuesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presupuestoxitem`
--

DROP TABLE IF EXISTS `presupuestoxitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presupuestoxitem` (
  `idPresupuestoXItem` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `idItem` int(11) DEFAULT NULL,
  `idPresupuesto` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPresupuestoXItem`),
  KEY `FK_22rv73s0q9tbe53k97hfhoo91` (`idItem`),
  KEY `FK_70dk09th8oe67m3p4xlydpeic` (`idPresupuesto`),
  CONSTRAINT `FK_22rv73s0q9tbe53k97hfhoo91` FOREIGN KEY (`idItem`) REFERENCES `item` (`idItem`),
  CONSTRAINT `FK_70dk09th8oe67m3p4xlydpeic` FOREIGN KEY (`idPresupuesto`) REFERENCES `presupuesto` (`idPresupuesto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuestoxitem`
--

LOCK TABLES `presupuestoxitem` WRITE;
/*!40000 ALTER TABLE `presupuestoxitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `presupuestoxitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `idProveedor` int(11) NOT NULL AUTO_INCREMENT,
  `cuit` int(11) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `idDireccion` int(11) DEFAULT NULL,
  PRIMARY KEY (`idProveedor`),
  KEY `FK_mtkyiey39pqrruut2a64npmqu` (`idDireccion`),
  CONSTRAINT `FK_mtkyiey39pqrruut2a64npmqu` FOREIGN KEY (`idDireccion`) REFERENCES `direccion` (`idDireccion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincia`
--

DROP TABLE IF EXISTS `provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provincia` (
  `idProvincia` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `idPais` int(11) DEFAULT NULL,
  PRIMARY KEY (`idProvincia`),
  KEY `FK_b9jhxer3uxmd5av6ijni76qbp` (`idPais`),
  CONSTRAINT `FK_b9jhxer3uxmd5av6ijni76qbp` FOREIGN KEY (`idPais`) REFERENCES `pais` (`idPais`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincia`
--

LOCK TABLES `provincia` WRITE;
/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
INSERT INTO `provincia` VALUES (1,'bsas','buenos aires',1);
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resultadovalidacion`
--

DROP TABLE IF EXISTS `resultadovalidacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resultadovalidacion` (
  `idValidacion` int(11) NOT NULL AUTO_INCREMENT,
  `detalles` varchar(255) DEFAULT NULL,
  `fechaValidacion` date DEFAULT NULL,
  `resultado` bit(1) DEFAULT NULL,
  `idEgreso` int(11) DEFAULT NULL,
  PRIMARY KEY (`idValidacion`),
  KEY `FK_pyevnrnu2pifn9f196us5bib5` (`idEgreso`),
  CONSTRAINT `FK_pyevnrnu2pifn9f196us5bib5` FOREIGN KEY (`idEgreso`) REFERENCES `egreso` (`idEgreso`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resultadovalidacion`
--

LOCK TABLES `resultadovalidacion` WRITE;
/*!40000 ALTER TABLE `resultadovalidacion` DISABLE KEYS */;
INSERT INTO `resultadovalidacion` VALUES (1,'aprobadisimo',NULL,_binary '',1);
/*!40000 ALTER TABLE `resultadovalidacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revisor`
--

DROP TABLE IF EXISTS `revisor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revisor` (
  `idRevisor` int(11) NOT NULL AUTO_INCREMENT,
  `idEgreso` int(11) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`idRevisor`),
  KEY `FK_jqb2hfx657r4lf2xa8oy1aoox` (`idEgreso`),
  KEY `FK_f812xgg8axvtkbh56mytrjday` (`idUsuario`),
  CONSTRAINT `FK_f812xgg8axvtkbh56mytrjday` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`),
  CONSTRAINT `FK_jqb2hfx657r4lf2xa8oy1aoox` FOREIGN KEY (`idEgreso`) REFERENCES `egreso` (`idEgreso`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revisor`
--

LOCK TABLES `revisor` WRITE;
/*!40000 ALTER TABLE `revisor` DISABLE KEYS */;
INSERT INTO `revisor` VALUES (1,1,1);
/*!40000 ALTER TABLE `revisor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `idRol` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'admin');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rolxpermiso`
--

DROP TABLE IF EXISTS `rolxpermiso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rolxpermiso` (
  `idRolXPermiso` int(11) NOT NULL AUTO_INCREMENT,
  `idPermiso` int(11) DEFAULT NULL,
  `idRol` int(11) DEFAULT NULL,
  PRIMARY KEY (`idRolXPermiso`),
  KEY `FK_61u6u9tw7cp2xposp37ohwg8p` (`idPermiso`),
  KEY `FK_6indttrovj5ip1137nyh64w62` (`idRol`),
  CONSTRAINT `FK_61u6u9tw7cp2xposp37ohwg8p` FOREIGN KEY (`idPermiso`) REFERENCES `permiso` (`idPermiso`),
  CONSTRAINT `FK_6indttrovj5ip1137nyh64w62` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolxpermiso`
--

LOCK TABLES `rolxpermiso` WRITE;
/*!40000 ALTER TABLE `rolxpermiso` DISABLE KEYS */;
INSERT INTO `rolxpermiso` VALUES (1,1,1);
/*!40000 ALTER TABLE `rolxpermiso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `clave` varchar(255) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `nombreUsuario` varchar(255) DEFAULT NULL,
  `idbandejamensaje` int(11) DEFAULT NULL,
  `idEntidadJuridica` int(11) DEFAULT NULL,
  `idRol` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `FK_1pfbi2e0l2ol8hmgxtrxepaem` (`idbandejamensaje`),
  KEY `FK_hqdyxu8jhh4tf5uilxw50h4um` (`idEntidadJuridica`),
  KEY `FK_1slat4m0e913nfd7t70pgg2mv` (`idRol`),
  CONSTRAINT `FK_1pfbi2e0l2ol8hmgxtrxepaem` FOREIGN KEY (`idbandejamensaje`) REFERENCES `bandejamensaje` (`idbandejamensaje`),
  CONSTRAINT `FK_1slat4m0e913nfd7t70pgg2mv` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`),
  CONSTRAINT `FK_hqdyxu8jhh4tf5uilxw50h4um` FOREIGN KEY (`idEntidadJuridica`) REFERENCES `entidadjuridica` (`idEntidadJuridica`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'qwerty',NULL,'test@test.com','tester',1,NULL,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-20 12:17:19
