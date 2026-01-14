import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL_BASE = DatabaseConfig.get("db.urlBase");
    private static final String DATABASE = DatabaseConfig.get("db.database");
    private static final String USER = DatabaseConfig.get("db.user");
    private static final String PASSWORD = DatabaseConfig.get("db.password");

    public static void main(String[] args) {
        criarBancoETabela();

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n========== SISTEMA JAVA (CRM) ==========");
            System.out.println("1 - Cadastrar novo usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Atualizar usuário");
            System.out.println("4 - Excluir usuário");
            System.out.println("5 - Buscar por CPF"); // Nova opção
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarUsuario(scanner);
                case 2 -> listarUsuarios();
                case 3 -> atualizarUsuario(scanner);
                case 4 -> excluirUsuario(scanner);
                case 5 -> buscarPorCpf(scanner);
                case 6 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 6);

        scanner.close();
    }

    private static Connection conectar(String database) throws SQLException {
        return DriverManager.getConnection(URL_BASE + database, USER, PASSWORD);
    }

    private static void criarBancoETabela() {
        try (Connection conn = DriverManager.getConnection(URL_BASE, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE);
        } catch (SQLException e) {
            System.out.println("Erro ao criar banco: " + e.getMessage());
        }

        String sqlTabela = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nome VARCHAR(100) NOT NULL,
                cpf VARCHAR(11) NOT NULL UNIQUE,
                email VARCHAR(100) NOT NULL UNIQUE
            )
        """;

        try (Connection conn = conectar(DATABASE);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sqlTabela);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }


    private static void buscarPorCpf(Scanner scanner) {
        System.out.print("Digite o CPF para busca (apenas números): ");
        String cpfBusca = scanner.nextLine();

        String sql = "SELECT * FROM usuarios WHERE cpf = ?";

        try (Connection conn = conectar(DATABASE);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpfBusca);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- Usuário Encontrado ---");
                System.out.printf("ID: %d%nNome: %s%nCPF: %s%nE-mail: %s%n",
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"));
            } else {
                System.out.println("Nenhum usuário encontrado com o CPF: " + cpfBusca);
            }
        } catch (SQLException e) {
            System.out.println("Erro na busca: " + e.getMessage());
        }
    }


    private static void cadastrarUsuario(Scanner scanner) {
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        System.out.print("E-mail: "); String email = scanner.nextLine();
        String sql = "INSERT INTO usuarios (nome, cpf, email) VALUES (?, ?, ?)";
        try (Connection conn = conectar(DATABASE); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome); stmt.setString(2, cpf); stmt.setString(3, email);
            stmt.executeUpdate();
            System.out.println("Cadastrado!");
        } catch (SQLException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private static void listarUsuarios() {
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = conectar(DATABASE);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Lista de Usuários ---");

            boolean encontrouUsuario = false;

            while (rs.next()) {
                encontrouUsuario = true;
                System.out.printf("ID: %d | Nome: %-15s | CPF: %-11s | E-mail: %s%n",
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"));
            }

            if (!encontrouUsuario) {
                System.out.println("=> Nenhum usuário cadastrado no sistema até o momento.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
    }

    private static void atualizarUsuario(Scanner scanner) {
        System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Novo Nome: "); String nome = scanner.nextLine();
        System.out.print("Novo CPF: "); String cpf = scanner.nextLine();
        System.out.print("Novo Email: "); String email = scanner.nextLine();
        String sql = "UPDATE usuarios SET nome = ?, cpf = ?, email = ? WHERE id = ?";
        try (Connection conn = conectar(DATABASE); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome); stmt.setString(2, cpf); stmt.setString(3, email); stmt.setInt(4, id);
            if (stmt.executeUpdate() > 0) System.out.println("Atualizado!");
        } catch (SQLException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private static void excluirUsuario(Scanner scanner) {
        System.out.print("ID: "); int id = scanner.nextInt();
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = conectar(DATABASE); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            if (stmt.executeUpdate() > 0) System.out.println("Excluído!");
        } catch (SQLException e) { System.out.println("Erro: " + e.getMessage()); }
    }
}