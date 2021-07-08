local key = KEYS[1]
local limitCount = ARGV[1]
local limitTime = ARGV[2]
local current = redis.call('get',key);
if current then
    redis.call('INCRBY',key,"1")
    return current+1
else
    redis.call('set',key,"1")
    redis.call("expire",key,limitTime)
    return 1
end