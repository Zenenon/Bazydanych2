DELIMITER //

CREATE PROCEDURE PrzypiszStatusMaszyny(
    IN maszynaID INT,
    IN opisStatusu VARCHAR(50)
)
BEGIN
    DECLARE statusID INT DEFAULT NULL;

    -- Sprawdzenie, czy status o podanym opisie istnieje
    SELECT ID INTO statusID
    FROM Statusy
    WHERE Opis = opisStatusu;

    -- Jeśli status nie istnieje, utwórz go
    IF statusID IS NULL THEN
        INSERT INTO Statusy (Opis)
        VALUES (opisStatusu);

        -- Pobranie ID nowo utworzonego statusu
        SELECT LAST_INSERT_ID() INTO statusID;
    END IF;

    -- Przypisanie statusu maszynie
    UPDATE Maszyny
    SET Status = statusID
    WHERE ID = maszynaID;

END //

DELIMITER ;
