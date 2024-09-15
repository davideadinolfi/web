-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Set 10, 2024 alle 21:52
-- Versione del server: 10.4.25-MariaDB
-- Versione PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `webdb`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `caratteristica`
--

CREATE TABLE `caratteristica` (
  `id` int(11) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `descrizione` varchar(255) NOT NULL,
  `id_categoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `descrizione` varchar(255) NOT NULL,
  `id_categoriaPadre` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `categoria`
--

INSERT INTO `categoria` (`id`, `nome`, `descrizione`, `id_categoriaPadre`) VALUES
(4, 'pc', 'pc', NULL),
(5, 'pc fisso', 'pc fisso', 4),
(6, 'pc portatile', 'pc portatile', 4),
(7, 'monitor', 'monitor', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `notifica`
--

CREATE TABLE `notifica` (
  `ID` int(11) NOT NULL,
  `ID_utente` int(11) NOT NULL,
  `ID_richiesta_acquisto` int(11) NOT NULL,
  `messaggio` varchar(255) NOT NULL,
  `data` datetime NOT NULL,
  `visualizzato` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `proposta_acquisto`
--

CREATE TABLE `proposta_acquisto` (
  `ID` int(11) NOT NULL,
  `ID_richiesta_acquisto` int(11) NOT NULL,
  `ID_tecnico` int(11) NOT NULL,
  `nome_produttore` varchar(255) NOT NULL,
  `nome_prodotto` varchar(255) NOT NULL,
  `codice_prodotto` int(11) NOT NULL,
  `prezzo` int(11) NOT NULL,
  `URL` varchar(512) NOT NULL,
  `note` varchar(1024) NOT NULL,
  `stato proposta` enum('approvato','respinto','ordinato','terminato','NC','NF') NOT NULL,
  `nota respinta` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `richiesta_acquisto`
--

CREATE TABLE `richiesta_acquisto` (
  `id` int(11) NOT NULL,
  `id_ordinante` int(11) NOT NULL,
  `id_tecnico` int(11) DEFAULT NULL,
  `id_categoria` int(11) NOT NULL,
  `data_richiesta` datetime NOT NULL,
  `note` varchar(1024) NOT NULL,
  `stato_richiesta` enum('attesaTecnico','attesaOrdinante','ordinato','concluso') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `richiesta_caratteristica`
--

CREATE TABLE `richiesta_caratteristica` (
  `id` int(11) NOT NULL,
  `id_richiesta_acquisto` int(11) NOT NULL,
  `id_caratteristica` int(11) NOT NULL,
  `specifica` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `id` int(11) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `cognome` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  `password` varchar(128) NOT NULL,
  `ruolo` enum('admin','tecnico','utente') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`id`, `nome`, `cognome`, `email`, `password`, `ruolo`) VALUES
(1, 'admin', 'admin', 'admin@email.com', 'admin', 'admin'),
(3, 'a', 'a', 'a', '60bddb4f4d5b4317d3faec87756f77909e0a2e6ab80e8a8bae3eceefeb354ffe52a2414e67734acd6b2b151f1ca271b6', 'admin'),
(4, 'u', 'u', 'u', '8b0239756676a129d02e725035c89edd641112b47836a5bcddce1fb082d3201dbb919a523c0fd13d91de75c942d10ac5', 'utente'),
(5, 't', 't', 't', '641869496bb3687c33b9b74d56c720317f68d61b40284a1de91b8132c5ca9da19635c24ad75c3330877567135be1d0be', 'tecnico');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `caratteristica`
--
ALTER TABLE `caratteristica`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- Indici per le tabelle `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_categoriaPadre` (`id_categoriaPadre`);

--
-- Indici per le tabelle `notifica`
--
ALTER TABLE `notifica`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_utente` (`ID_utente`),
  ADD KEY `ID_richiesta_acquisto` (`ID_richiesta_acquisto`);

--
-- Indici per le tabelle `proposta_acquisto`
--
ALTER TABLE `proposta_acquisto`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_richiesta_acquisto` (`ID_richiesta_acquisto`),
  ADD KEY `ID_tecnico` (`ID_tecnico`);

--
-- Indici per le tabelle `richiesta_acquisto`
--
ALTER TABLE `richiesta_acquisto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_ordinante` (`id_ordinante`),
  ADD KEY `id_tecnico` (`id_tecnico`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- Indici per le tabelle `richiesta_caratteristica`
--
ALTER TABLE `richiesta_caratteristica`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_richiesta_acquisto` (`id_richiesta_acquisto`),
  ADD KEY `id_caratteristica` (`id_caratteristica`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `caratteristica`
--
ALTER TABLE `caratteristica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT per la tabella `notifica`
--
ALTER TABLE `notifica`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `proposta_acquisto`
--
ALTER TABLE `proposta_acquisto`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `richiesta_acquisto`
--
ALTER TABLE `richiesta_acquisto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `richiesta_caratteristica`
--
ALTER TABLE `richiesta_caratteristica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `categoria`
--
ALTER TABLE `categoria`
  ADD CONSTRAINT `categoria_ibfk_1` FOREIGN KEY (`id_categoriaPadre`) REFERENCES `categoria` (`id`);

--
-- Limiti per la tabella `notifica`
--
ALTER TABLE `notifica`
  ADD CONSTRAINT `notifica_ibfk_1` FOREIGN KEY (`ID_utente`) REFERENCES `utenti` (`id`),
  ADD CONSTRAINT `notifica_ibfk_2` FOREIGN KEY (`ID_richiesta_acquisto`) REFERENCES `richiesta_acquisto` (`id`);

--
-- Limiti per la tabella `proposta_acquisto`
--
ALTER TABLE `proposta_acquisto`
  ADD CONSTRAINT `proposta_acquisto_ibfk_1` FOREIGN KEY (`ID_richiesta_acquisto`) REFERENCES `richiesta_acquisto` (`id`),
  ADD CONSTRAINT `proposta_acquisto_ibfk_2` FOREIGN KEY (`ID_tecnico`) REFERENCES `utenti` (`id`);

--
-- Limiti per la tabella `richiesta_acquisto`
--
ALTER TABLE `richiesta_acquisto`
  ADD CONSTRAINT `richiesta_acquisto_ibfk_1` FOREIGN KEY (`id_ordinante`) REFERENCES `utenti` (`id`),
  ADD CONSTRAINT `richiesta_acquisto_ibfk_2` FOREIGN KEY (`id_tecnico`) REFERENCES `utenti` (`id`),
  ADD CONSTRAINT `richiesta_acquisto_ibfk_3` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`);

--
-- Limiti per la tabella `richiesta_caratteristica`
--
ALTER TABLE `richiesta_caratteristica`
  ADD CONSTRAINT `richiesta_caratteristica_ibfk_1` FOREIGN KEY (`id_richiesta_acquisto`) REFERENCES `richiesta_acquisto` (`id`),
  ADD CONSTRAINT `richiesta_caratteristica_ibfk_2` FOREIGN KEY (`id_caratteristica`) REFERENCES `caratteristica` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
