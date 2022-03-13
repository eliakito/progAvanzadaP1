package paquete;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableWithLabelsTest {

    @Test
    @DisplayName("Test getRowAt()")
    void getRowAt() {
        TableWithLabels tableWithLabels = new TableWithLabels();
        // TEST FILA 0
        String label = "Iris-setosa";
        assertEquals(label, tableWithLabels.getRowAt(0).getLabel());

        // TEST FILA 53
        label = "Iris-versicolor";
        assertEquals(label, tableWithLabels.getRowAt(53).getLabel());

        // TEST FILA 149 (ULTIMA)
        label = "Iris-virginica";
        assertEquals(label, tableWithLabels.getRowAt(149).getLabel());
    }
}