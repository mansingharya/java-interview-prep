package LLD.SplitwiseSystem;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


class User {
    private final String userId;
    private final String name;
    private final String email;

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

class Split {
    private final String userId;
    private final double amount;

    public Split(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "\nSplit{" +
                "userId='" + userId + '\'' +
                ", amount=" + amount +
                '}';
    }
}


enum SplitType {EQUAL, EXACT, PERCENTAGE }

class Expense {
    private final String expenseId;
    private final String desc;
    private final double amount;
    private final String paidByUserId;
    private final List<Split> splits;
    private final SplitType splitType;
    private final String groupId;
    private final LocalDateTime createdAt;

    public Expense(String expenseId, String desc, double amount, String paidByUserId, List<Split> splits, SplitType splitType, String groupId) {
        this.expenseId = expenseId;
        this.desc = desc;
        this.amount = amount;
        this.paidByUserId = paidByUserId;
        this.splits = splits;
        this.splitType = splitType;
        this.groupId = groupId;
        this.createdAt = LocalDateTime.now();
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getDesc() {
        return desc;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaidByUserId() {
        return paidByUserId;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public String getGroupId() {
        return groupId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "\nExpense{" +
                "expenseId='" + expenseId + '\'' +
                ", desc='" + desc + '\'' +
                ", amount=" + amount +
                ", paidByUserId='" + paidByUserId + '\'' +
                ", splits=" + splits +
                ", splitType=" + splitType +
                ", groupId='" + groupId + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

class Group {
    private final String groupId;
    private final String name;
    private final List<String> memberIds;

    public Group(String groupId, String name, List<String> memberIds) {
        this.groupId = groupId;
        this.name = name;
        this.memberIds = memberIds;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public List<String> getMemberIds() {
        return memberIds;
    }

    public void addMember(String userId) {
        if ( !memberIds.contains(userId)) {
            memberIds.add(userId);
        }
    }

    @Override
    public String toString() {
        return "\nGroup{" +
                "groupId='" + groupId + '\'' +
                ", name='" + name + '\'' +
                ", memberIds=" + memberIds +
                '}';
    }
}


interface SplitStrategy {

    SplitType getSplitType();

    List<Split> calculateSplit(double totalAmount, List<User> participants, Map<String, Double> metaData);
}

class EqualSplitStrategy implements SplitStrategy {

    @Override
    public SplitType getSplitType() {
        return SplitType.EQUAL;
    }

    @Override
    public List<Split> calculateSplit(double totalAmount, List<User> participants, Map<String, Double> metaData) {

        if (participants == null || participants.isEmpty()) {
            throw new IllegalArgumentException("Participants list cannot be empty");
        }

        double share = totalAmount / participants.size();
        List<Split> splits = new ArrayList<>();

        for (User u : participants) {
            splits.add(new Split(u.getUserId(), share));
        }

        return splits;
    }
}


class PercentageStrategy implements SplitStrategy {

    @Override
    public SplitType getSplitType() {
        return SplitType.PERCENTAGE;
    }

    @Override
    public List<Split> calculateSplit(double totalAmount, List<User> participants, Map<String, Double> metaData) {

        if (participants == null || participants.isEmpty()) {
            throw new IllegalArgumentException("Participants cannot be empty");
        }

        if (metaData == null || metaData.isEmpty()) {
            throw new IllegalArgumentException("MetaData is required for percentage");
        }

        double totalPer = 0.0;
        for (double d : metaData.values()) {
            totalPer += d;
        }

        if (Math.abs(totalPer - 100) > 0.01) {
            throw new RuntimeException("Percentage sum must be 100");
        }

        List<Split> splits = new ArrayList<>();
        for (User u : participants) {
            Double userShareInPer = metaData.get(u.getUserId());
            if (userShareInPer == null || userShareInPer <= 0) {
                throw new RuntimeException("Missing or invalid percentage for user: " + u.getUserId());
            }
            splits.add(new Split(u.getUserId(), totalAmount * userShareInPer / 100.0));
        }
        return splits;
    }
}

class ExactStrategy implements SplitStrategy {

    @Override
    public SplitType getSplitType() {
        return SplitType.EXACT;
    }

    @Override
    public List<Split> calculateSplit(double totalAmount, List<User> participants, Map<String, Double> metaData) {

        if (participants == null || participants.isEmpty()) {
            throw new IllegalArgumentException("Participants cannot be empty");
        }

        if (metaData == null || metaData.isEmpty()) {
            throw new IllegalArgumentException("MetaData is required for percentage");
        }

        double totalExactAmount = 0.0;
        for (double d : metaData.values()) {
            totalExactAmount += d;
        }

        if (Math.abs(totalExactAmount - totalAmount) > 0.01) {
            throw new RuntimeException("Exact amount sum must be same as total expense amount");
        }

        List<Split> splits = new ArrayList<>();
        for (User u : participants) {
            Double exactAmt = metaData.get(u.getUserId());
            if (exactAmt == null || exactAmt <= 0) {
                throw new RuntimeException("Missing or negative exact amount");
            }
            splits.add(new Split(u.getUserId(), exactAmt));
        }
        return splits;
    }
}

interface UserRepository {
    void save(User user);
    Optional<User> findByUserId(String userId);
    List<User> findAll();
}

class InMemoryRepository implements UserRepository {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}


interface GroupRepository {
    void save(Group group);
    Optional<Group> findByGroupId(String groupId);
    List<Group> findAll();
}

class InMemoryGroupRepository implements GroupRepository {
    private final Map<String, Group> groups = new ConcurrentHashMap<>();

    @Override
    public void save(Group group) {
        groups.put(group.getGroupId(), group);
    }

    @Override
    public Optional<Group> findByGroupId(String groupId) {
        return Optional.ofNullable(groups.get(groupId));
    }

    @Override
    public List<Group> findAll() {
        return new ArrayList<>(groups.values());
    }
}


interface ExpenseRepository {
    void save(Expense expense);
    Optional<Expense> findByExpenseId(String expenseId);
    List<Expense> fidAll();

    void delete(String expenseId);
    List<Expense> findByUserId(String userId);
    List<Expense> findByGroupId(String groupId);
}

class InMemoryExpenseRepository implements ExpenseRepository {
    private final Map<String, Expense> expenses = new ConcurrentHashMap<>();

    @Override
    public void save(Expense expense) {
        expenses.put(expense.getExpenseId(), expense);
    }

    @Override
    public Optional<Expense> findByExpenseId(String expenseId) {
        return Optional.ofNullable(expenses.get(expenseId));
    }

    @Override
    public List<Expense> fidAll() {
        return new ArrayList<>(expenses.values());
    }

    @Override
    public void delete(String expenseId) {
        expenses.remove(expenseId);
    }

    @Override
    public List<Expense> findByUserId(String userId) {
        List<Expense> expenseList = new ArrayList<>();

        for (Expense e : expenses.values()) {
            // Check if userId paid this expense
            if (e.getPaidByUserId().equals(userId)) {
                expenseList.add(e);
                continue;
            }

            // Check if userId was part of this expense
            for (Split s : e.getSplits()) {
                if (s.getUserId().equals(userId)) {
                    expenseList.add(e);
                    break;
                }
            }
        }

        expenseList.sort(Comparator.comparing(Expense::getCreatedAt).reversed());
        return expenseList;
    }

    @Override
    public List<Expense> findByGroupId(String groupId) {
        List<Expense> expenseList = new ArrayList<>();

        for (Expense e : expenses.values()) {
            if (e.getGroupId().equals(groupId)) {
                expenseList.add(e);
            }
        }

        expenseList.sort(Comparator.comparing(Expense::getCreatedAt).reversed());
        return expenseList;
    }
}


class BalanceService {

    private final Map<String, Map<String, Double>> balanceSheet = new ConcurrentHashMap<>();


    void updateBalance(String creditorId, String debtorId, double amount) {
        balanceSheet.computeIfAbsent(creditorId, k-> new ConcurrentHashMap<>())
                .merge(debtorId, amount, Double::sum);

        balanceSheet.computeIfAbsent(debtorId, k-> new ConcurrentHashMap<>())
                .merge(creditorId, -amount, Double::sum);
    }


    public double getNetBalance(String userId) {
        double sum = 0.0;
        for (Double val : balanceSheet.getOrDefault(userId, Collections.emptyMap()).values()) {
            sum += val;
        }
        return sum;
    }

    public Map<String, Map<String, Double>> getAllBalance() {
        return Collections.unmodifiableMap(balanceSheet);
    }

    public List<String> showBalanceAll() {
        List<String> lines = new ArrayList<>();

        for (Map.Entry<String, Map<String, Double>> outer : balanceSheet.entrySet()) {
            String creditorId = outer.getKey();

            for (Map.Entry<String, Double> inner : outer.getValue().entrySet()) {
                double amount = inner.getValue();
                if (amount > 0.01) {
                    lines.add(inner.getKey() + " owes " + creditorId + " : " + amount);
                }
            }

        }
        return lines;
    }

    public Map<String, Double> showBalanceOfUser(String userId) {
        return Collections.unmodifiableMap(balanceSheet.getOrDefault(userId, Collections.emptyMap()));
    }
}



class ExpenseService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;

    private final BalanceService balanceService;

    private final AtomicInteger counter = new AtomicInteger(1);


    public ExpenseService(UserRepository userRepository, GroupRepository groupRepository, ExpenseRepository expenseRepository, BalanceService balanceService) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.balanceService = balanceService;
    }

    public User addUser(String userId, String name, String email) {
        User user = new User(userId, name, email);
        userRepository.save(user);
        return user;
    }

    public Group createGroup(String groupId, String name, List<String> membersIds) {
        for (String userId : membersIds) {
            if (userRepository.findByUserId(userId).isEmpty()) {
                throw new RuntimeException("User Not Found");
            }
        }
        Group group = new Group(groupId, name, membersIds);
        groupRepository.save(group);
        return group;
    }

    public void addMemberToGroup(String groupId, String userId) {
        Group group = groupRepository.findByGroupId(groupId).orElseThrow(() -> new IllegalArgumentException("Group No Found"));

        userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        group.addMember(userId);
    }

    public Expense addExpense(String desc, double amount, String paidByUserId, List<String> participantIds,
                              SplitStrategy strategy, Map<String, Double> metaInfo, String groupId) {

        userRepository.findByUserId(paidByUserId).orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        List<User> participants = new ArrayList<>();
        for (String id : participantIds) {
            User u = userRepository.findByUserId(id).orElseThrow(() -> new IllegalArgumentException("No User"));
            participants.add(u);
        }

        List<Split> splits = strategy.calculateSplit(amount, participants, metaInfo);
        SplitType splitType = strategy.getSplitType();
        String expId = "EXP-" + counter.getAndIncrement();

        Expense expense = new Expense(expId, desc, amount, paidByUserId, splits, splitType, groupId);
        expenseRepository.save(expense);

        for (Split split : splits) {
            if ( !split.getUserId().equals(paidByUserId)) {
                balanceService.updateBalance(paidByUserId, split.getUserId(), split.getAmount());
            }
        }

        return expense;
    }


    public void removeExpense(String expenseId) {
        Expense expense = expenseRepository.findByExpenseId(expenseId).orElseThrow(() -> new RuntimeException("No Expense found"));

        for (Split split : expense.getSplits()) {
            if ( !split.getUserId().equals(expense.getPaidByUserId())) {
                balanceService.updateBalance(expense.getPaidByUserId(), split.getUserId(), -split.getAmount());
            }
        }

        expenseRepository.delete(expenseId);
    }

    public List<Expense> getExpensesByGroup(String groupId) {
        return expenseRepository.findByGroupId(groupId);
    }

}


class SplitStrategyFactory {
    static SplitStrategy getStrategy(SplitType splitType) {
        return switch (splitType) {
            case EQUAL -> new EqualSplitStrategy();
            case EXACT -> new ExactStrategy();
            case PERCENTAGE -> new PercentageStrategy();
            default -> throw new IllegalArgumentException("Unknown");
        };
    }
}




class Transaction {
    private final String fromUserId;
    private final String toUserId;
    private final double amount;

    public Transaction(String fromUserId, String toUserId, double amount) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s : %.2f", fromUserId, toUserId, amount);
    }
}


public class SplitwiseSystem {

    public static void main(String [] args) {

    }
}


