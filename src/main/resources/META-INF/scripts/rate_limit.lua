local current = tonumber(redis.call('GET', KEYS[1]))
if current ~= nil and current > 10 then
    return false
else
    local value = tonumber(redis.call("INCR", KEYS[1]))
    if value == 1 then
        redis.call("EXPIRE", KEYS[1], 1)
    end
    return true
end
