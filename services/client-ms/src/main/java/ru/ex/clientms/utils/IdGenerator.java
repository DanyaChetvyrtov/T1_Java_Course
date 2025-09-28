package ru.ex.clientms.utils;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class IdGenerator {

    private final JdbcTemplate jdbcTemplate;

    // SQL: atomic upsert — вставляем новую строку с last_value=1, иначе увеличиваем last_value на 1
    private static final String UPSERT_SQL = """
                INSERT INTO client_sequence(region_code, branch_code, last_value)
                VALUES (?, ?, 1)
                ON CONFLICT (region_code, branch_code)
                DO UPDATE SET last_value = client_sequence.last_value + 1
                RETURNING last_value;
            """;

    /**
     * Возвращает следующий порядковый номер (NNNNNNNN) для пары (region, branch).
     * Это атомарная операция в Postgres (один SQL-запрос).
     *
     * @param region region code (1..99)
     * @param branch branch code (1..99)
     * @return следующее значение last_value (>=1)
     */
    @Transactional
    public long nextSequenceAtomic(int region, int branch) {
        Long next = jdbcTemplate.queryForObject(
                UPSERT_SQL,
                new Object[]{region, branch},
                Long.class
        );
        if (next == null) {
            throw new IllegalStateException("Failed to generate client sequence for region=" + region + ", branch=" + branch);
        }
        return next;
    }

    /**
     * Возвращает скомпонованный clientId в формате XXFFNNNNNNNN
     *
     * @param region 1..99
     * @param branch 1..99
     * @return строка clientId, например "770100000001"
     */
    public String nextClientId(int region, int branch) {
        long seq = nextSequenceAtomic(region, branch);
        // XX FF NNNNNNNN (8 цифр для seq)
        return String.format("%02d%02d%08d", region, branch, seq);
    }
}

