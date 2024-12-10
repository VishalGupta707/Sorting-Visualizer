import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SortingVisualizer {
    private JFrame frame;
    private JPanel panel;
    private JButton resetButton;
    private JList<String> algorithmList;
    private JSlider speedSlider;
    private int[] array;
    private final int ARRAY_SIZE = 50;
    private int speed = 50;

    public SortingVisualizer() {
       
        frame = new JFrame("Sorting Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS)); // Vertical layout

        resetButton = new JButton("Reset");
        JButton startButton = new JButton("Start");
        JButton speed1x = new JButton("1x");
        JButton speed2x = new JButton("2x");
        JButton speed3x = new JButton("3x");

        String[] algorithms = {"Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort"};
        algorithmList = new JList<>(algorithms);
        algorithmList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        algorithmList.setVisibleRowCount(5);
        JScrollPane scrollPane = new JScrollPane(algorithmList);

        controlPanel.add(resetButton);
        controlPanel.add(Box.createVerticalStrut(10)); // Add vertical spacing
        controlPanel.add(scrollPane);
        controlPanel.add(Box.createVerticalStrut(10)); // Add vertical spacing
        controlPanel.add(new JLabel("Speed:"));
        controlPanel.add(speed1x);
        controlPanel.add(speed2x);
        controlPanel.add(speed3x);
        controlPanel.add(Box.createVerticalStrut(10)); // Add vertical spacing
        controlPanel.add(startButton);

        // Add listeners
        resetButton.addActionListener(e -> resetArray());
        speed1x.addActionListener(e -> speed = 100); // 1x speed
        speed2x.addActionListener(e -> speed = 50);  // 2x speed
        speed3x.addActionListener(e -> speed = 25);  // 3x speed

        startButton.addActionListener(e -> {
            String selectedAlgorithm = algorithmList.getSelectedValue();
            if (selectedAlgorithm != null) {
                startSorting(selectedAlgorithm);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select an algorithm!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawArray(g);
            }
        };
        panel.setBackground(Color.BLACK);

        
        frame.add(controlPanel, BorderLayout.WEST);
        frame.add(panel, BorderLayout.CENTER);

        resetArray(); // Initialize the array
        frame.setVisible(true);
    }

    private void startSorting(String algorithm) {
        new Thread(() -> {
            switch (algorithm) {
                case "Bubble Sort":
                    SortingAlgorithms.bubbleSort(array, this, speed);
                    break;
                case "Selection Sort":
                    SortingAlgorithms.selectionSort(array, this, speed);
                    break;
                case "Insertion Sort":
                    SortingAlgorithms.insertionSort(array, this, speed);
                    break;
                case "Merge Sort":
                    SortingAlgorithms.mergeSort(array, this, speed);
                    break;
                case "Quick Sort":
                    SortingAlgorithms.quickSort(array, this, speed);
                    break;
            }
        }).start();

    }


    private void resetArray() {
        array = generateRandomArray(ARRAY_SIZE);
        panel.repaint();
    }

    private int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        int panelHeight = Math.max(panel.getHeight() - 50, 1); // Ensure positive height
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(panelHeight) + 10;
        }
        return array;
    }

    private void drawArray(Graphics g) {
        if (array == null) return;

        int barWidth = panel.getWidth() / array.length;
        for (int i = 0; i < array.length; i++) {
            g.setColor(Color.CYAN);
            g.fillRect(i * barWidth, panel.getHeight() - array[i], barWidth - 2, array[i]);
        }
    }

    public void updateArray(int[] updatedArray) {
        this.array = updatedArray;
        panel.repaint();
        try {
            Thread.sleep(speed); // Use dynamic speed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
