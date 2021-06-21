#import hashlib

################
################
################

rt2 = [2,3,5,7,11,13,17,19]
rt3 = [2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,
       101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,
       193,197,199,211,223,227,229,233,239,241,251,257,263,269,271,277,281,283,
       293,307,311]

#32bits of the fractional parts of sq root (8 primes)
h0 = format(f'{int((2**(1/2) - float("{:.0f}".format(2**(1/2)))) * 2**32):032b}')
h1 = format(f'{int((3**(1/2) - int(3**(1/2))) * 2**32):032b}')
h2 = format(f'{int((5**(1/2) - float("{:.0f}".format(5**(1/2)))) * 2**32):032b}')
h3 = format(f'{int((7**(1/2) - int(7**(1/2))) * 2**32):032b}')
h4 = format(f'{int((11**(1/2) - float("{:.0f}".format(11**(1/2)))) * 2**32):032b}')
h5 = format(f'{int((13**(1/2) - int(13**(1/2))) * 2**32):032b}')
h6 = format(f'{int((17**(1/2) - float("{:.0f}".format(17**(1/2)))) * 2**32):032b}')
h7 = format(f'{int((19**(1/2) - float("{:.0f}".format(19**(1/2)))) * 2**32):032b}')

def rt2s():
    res = []
    for i in range(len(rt2)):
        res.append(format(f'{int((rt2[i]**(1/2) - int(rt2[i]**(1/2))) * 2**32):032b}'))
    return res

#32bits of the fractional parts of cube root (64 primes)
def rt3s():
    res = []
    for i in range(len(rt3)):
        res.append(format(f'{int((rt3[i]**(1/3) - int(rt3[i]**(1/3))) * 2**32):032b}'))
    return res


def hashes(stringIn):
    #global msg, msgL, h0, h1, h2, h3, h4, h5, h6, h7
    
   # global h0, h1, h2, h3, h4, h5, h6, h7
    h0 = format(f'{int((2**(1/2) - float("{:.0f}".format(2**(1/2)))) * 2**32):032b}')
    h1 = format(f'{int((3**(1/2) - int(3**(1/2))) * 2**32):032b}')
    h2 = format(f'{int((5**(1/2) - float("{:.0f}".format(5**(1/2)))) * 2**32):032b}')
    h3 = format(f'{int((7**(1/2) - int(7**(1/2))) * 2**32):032b}')
    h4 = format(f'{int((11**(1/2) - float("{:.0f}".format(11**(1/2)))) * 2**32):032b}')
    h5 = format(f'{int((13**(1/2) - int(13**(1/2))) * 2**32):032b}')
    h6 = format(f'{int((17**(1/2) - float("{:.0f}".format(17**(1/2)))) * 2**32):032b}')
    h7 = format(f'{int((19**(1/2) - float("{:.0f}".format(19**(1/2)))) * 2**32):032b}')

    msg = ""
    msgL = ""
    
    for i in range(len(stringIn)):
        cv = ord(stringIn[i])#loop chars
        ##cb = bin(cv)[2:]#omit 0b from binary value
        cbb = format(f'{cv:08b}') # print as 8 bits
        cs = str(cbb)#parse to string
        msg += cs
        
    padNumber = paddingNo(msg)
    
    newMsg = msg+"1"
    #msgL = len(newMsg)
    #print("msg: ", len(msg))
    
    #######
    #padding must increase as the msg (L) increase   
    paddingn = padNumber*512-(len(newMsg)+64)
    for j in range(1,paddingn+1):
        newMsg += "0" 

    paddLen = format(f'{len(msg):0b}')
    padadd = 64-len(paddLen)
    
    for r in range(1,padadd+1):
        newMsg += "0"
    newMsg += paddLen
    
    mii = 0
    maa = 512
    #mchun = [newMsg[mii:maa] for i in range(10)]
    mchun = [newMsg[mii:maa]]
    for i in range(padNumber):
        mii += 512
        maa += 512
        mchun.append(newMsg[mii:maa])
    
    for i in range(padNumber):
        newMsg = mchun[i]
        #print(newMsg)
        
        mini = 0
        maxi = 32
        messageChunk = [newMsg[0:32]]
        
        for j in range(15):
            mini += 32
            maxi += 32
            messageChunk.append(newMsg[mini:maxi])
    
        for j in range(16,64):
            
            mlen = len(messageChunk)
            s0 = sig0(messageChunk[mlen-15])
            s1 = sig1(messageChunk[mlen-2])
            
            newBits = adders(messageChunk[mlen-16], s0, messageChunk[mlen-7], s1)
            #print(newBits)
            messageChunk.append(newBits)
            #print(messageChunk[i])
            #print()
        #print(messageChunk)
        
        a = h0
        b = h1
        c = h2
        d = h3
        e = h4
        f = h5
        g = h6
        h = h7
        
        k = rt3s()
        for j in range(64):
            S1 = sigma1(e)
            ch = cho(e,f,g)
            temp1 = adders5(h,S1,ch,k[j],messageChunk[j])
            
            S0 = sigma0(a)
            maj = mj(a,b,c)
            temp2 = adders2(S0, maj)
            
            h = g
            g = f
            f = e
            e = adders2(d, temp1)
            d = c
            c = b
            b = a
            a = adders2(temp1, temp2)
            
        h0 = adders2(h0, a)
        h1 = adders2(h1, b)
        h2 = adders2(h2, c)
        h3 = adders2(h3, d)
        h4 = adders2(h4, e)
        h5 = adders2(h5, f)
        h6 = adders2(h6, g)
        h7 = adders2(h7, h)
        
    digest = h0 + h1 + h2 + h3 + h4 + h5 + h6 + h7
    digestRes = hex(int(digest, 2))
    digestOb = digestRes[2:]
    
    if len(digestOb) < 64:
        digestOb = "0" + digestOb
    
    # print(digest)
    # print(len(digest))
    # print(hex(int(digest, 2)))
    #return hex(int(digest, 2))
    return digestOb
    

def paddingNo(msg):
    chunks = 1
    bsize = 447

    if len(msg) <= bsize: # total is 512
        chunks = 1
    elif len(msg) >= bsize+1:
        #bsize += 1
        while True:
            #print()
            bsize += 512
            chunks += 1
            if len(msg) <= bsize:
                break
        
    # print(len(msg))
    #print(chunks)
    # print(bsize)        
    #return chunks
    return chunks
    

def sig0(bits):
    a = rotr(bits,7)
    b = rotr(bits,18)
    c = shr(bits,3)
    
    res = xor(a,b,c)
    return res

def sig1(bits):
    a = rotr(bits,17)
    b = rotr(bits,19)
    c = shr(bits,10)
    
    res = xor(a,b,c)
    return res

def sigma0(bits):
    a = rotr(bits,2)
    b = rotr(bits,13)
    c = rotr(bits,22)
    
    res = xor(a,b,c)
    return res

def sigma1(bits):
    a = rotr(bits,6)
    b = rotr(bits,11)
    c = rotr(bits,25)
    
    res = xor(a,b,c)
    return res
    
def xor(a,b,c):
    res = ""
    for i in range(len(a)):
        if (ord(a[i]) ^ ord(b[i]) ^ ord(c[i])) == 48:
            res = res + "0"
        if (ord(a[i]) ^ ord(b[i]) ^ ord(c[i])) == 49:
            res = res + "1"
    return res

def adderzz(a):
    res = ""
    
def adders(a,b,c,d):
    res = ""
    aint = int(a, 2)
    bint = int(b, 2)
    cint = int(c, 2)
    dint = int(d, 2)
    
    r = aint + bint + cint + dint
    binr = str(format(f'{r:032b}'))
    if len(binr) <= 32:
        res = binr
    elif len(binr) > 32:
        mods = r % 2**32
        res = str(format(f'{mods:032b}'))

    return res

def adders5(a,b,c,d,e):
    res = ""
    aint = int(a, 2)
    bint = int(b, 2)
    cint = int(c, 2)
    dint = int(d, 2)
    eint = int(e, 2)
    
    r = aint + bint + cint + dint + eint
    binr = str(format(f'{r:032b}'))
    if len(binr) <= 32:
        res = binr
    elif len(binr) > 32:
        mods = r % 2**32
        
        res = str(format(f'{mods:032b}'))
    return res

def adders2(a,b):
    res = ""
    aint = int(a, 2)
    bint = int(b, 2)
    
    r = aint + bint
    binr = str(format(f'{r:032b}'))
    if len(binr) <= 32:
        res = binr
    elif len(binr) > 32:
        mods = r % 2**32
    
        res = str(format(f'{mods:032b}'))
    return res
    
def rotr(bits, rotnumber):
    for i in range(rotnumber):
        last_char = bits[-1]
        bits = bits[0:-1]
        bits = last_char + bits  
    return bits
    
def shr(bits, rotnumber):
    for i in range(rotnumber):
        bits = bits[0:-1]
        bits = "0" + bits  
    return bits

def cho(a,b,c):
    #use 'a' input to determine whether to take 'b' or 'c'
    res = ""
    for i in range(len(a)):
        if a[i] == "1":
            res += b[i]
        elif a[i] == "0":
            res += c[i]    
    return res

def mj(a,b,c):#
    #take majority input value
    res = ""
    for i in range(len(a)):
        if (int(a[i]) and int(b[i])) ^ (int(a[i]) and int(c[i])) ^ (int(b[i]) and int(c[i])):
            res += "1"
        else:
            res += "0"
    return res



msg = ""
for i in range(100):
    #print(msg)
    
    fake = hashes(msg)
    #print("f", i,": ", fake)
    
    encoded = msg.encode()
    #result = hashlib.sha256(encoded)
    #print("r", i,": ", result.hexdigest())
    
    msg = msg + "a"
    
    # if fake != result.hexdigest():
    #     print(msg)
    #     print("f", i,": ", fake)
    #     print("r", i,": ", result.hexdigest())


#hashes("1")