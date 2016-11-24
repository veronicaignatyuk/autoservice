package main.java.bntu.security;

import java.nio.ByteBuffer;

public class SHA1 {
	
	static int h0 = 0x67452301;
	static int h1 = 0xEFCDAB89;
	static int h2 = 0x98BADCFE;
	static int h3 = 0x10325476;
	static int h4 = 0xC3D2E1F0;

	static final int[] w = new int[80];
	static int a, b, c, d, e;
	static int f, k, temp;

	static final void addDigest(final ByteBuffer src) {
		
		// Process the message in successive 512-bit (64 byte) chunks:
		// break message into 512-bit (64 byte) chunks
		// Break chunk into sixteen 32-bit big-endian words w[i], 0 <= i <= 15.
		int i = 0;
		for (i = 0; i < 16; i++)
			w[i] = src.getInt(i * 4);
		for (i = 16; i < 80; i++)
			w[i] = 0;

		// Compute/add 1 digest line.
		// Extend the sixteen 32-bit (4 byte) words into eighty 32-bit (4 byte)
		// words:
		for (i = 16; i < 80; i++)
			w[i] = Integer.rotateLeft(w[i - 3] ^ w[i - 8] ^ w[i - 14]
					^ w[i - 16], 1);

		// Initialize hash value for this chunk:
		f = k = temp = 0;
		a = h0;
		b = h1;
		c = h2;
		d = h3;
		e = h4;

		for (i = 0; i < 20; i++) {
			f = (b & c) | ((~b) & d);
			k = 0x5A827999;
			finishValues(i);
		}
		for (i = 20; i < 40; i++) {
			f = b ^ c ^ d;
			k = 0x6ED9EBA1;
			finishValues(i);
		}
		for (i = 40; i < 60; i++) {
			f = (b & c) | (b & d) | (c & d);
			k = 0x8F1BBCDC;
			finishValues(i);
		}
		for (i = 60; i < 80; i++) {
			f = b ^ c ^ d;
			k = 0xCA62C1D6;
			finishValues(i);
		}

		// Add this chunk's hash to result so far:
		h0 += a;
		h1 += b;
		h2 += c;
		h3 += d;
		h4 += e;
	}

	static final void finishValues(final int i) {
		temp = Integer.rotateLeft(a, 5) + f + e + k + w[i];
		e = d;
		d = c;
		c = Integer.rotateLeft(b, 30);
		b = a;
		a = temp;
	}

	static final ByteBuffer pad(final ByteBuffer src, long wholeMsgLength) {
		final ByteBuffer padded = ByteBuffer.allocateDirect(128);
		padded.put(src);
		padded.put((byte) 128);

		if (padded.position() < 56) {
			while (padded.position() < 56)
				padded.put((byte) 0x0);
		} else {
			while (padded.position() < 120)
				padded.put((byte) 0x0);
		}

		padded.putLong(wholeMsgLength);
		padded.flip();
		return padded;
	}

	static final void processBytes(final byte[] bytes) {
		ByteBuffer src = ByteBuffer.wrap(bytes);

		if (src.limit() >= 64) {
			for (int i = 0, n = src.limit() / 64; i < n; i++) {
				final int offset = i * 64;
				src.position(offset).limit(offset + 64);
				// addDigest(buf);
				// Process the message in successive 512-bit (64 byte) chunks:
				// break message into 512-bit (64 byte) chunks
				// Break chunk into sixteen 32-bit big-endian words w[i], 0 <= i
				// <= 15.
				int j = 0;
				for (j = 0; j < 16; j++)
					w[j] = src.getInt(j * 4);
				for (j = 16; j < 80; j++)
					w[j] = 0;

				// Compute/add 1 digest line.
				// Extend the sixteen 32-bit (4 byte) words into eighty 32-bit
				// (4 byte) words:
				for (j = 16; j < 80; j++)
					w[j] = Integer.rotateLeft(w[j - 3] ^ w[j - 8] ^ w[j - 14]
							^ w[j - 16], 1);
				// Initialize hash value for this chunk:
				f = k = temp = 0;
				a = h0;
				b = h1;
				c = h2;
				d = h3;
				e = h4;

				for (j = 0; j < 20; j++) {
					f = (b & c) | ((~b) & d);
					k = 0x5A827999;
					finishValues(j);
				}
				for (j = 20; j < 40; j++) {
					f = b ^ c ^ d;
					k = 0x6ED9EBA1;
					finishValues(j);
				}
				for (j = 40; j < 60; j++) {
					f = (b & c) | (b & d) | (c & d);
					k = 0x8F1BBCDC;
					finishValues(j);
				}
				for (j = 60; j < 80; j++) {
					f = b ^ c ^ d;
					k = 0xCA62C1D6;
					finishValues(j);
				}

				// Add this chunk's hash to result so far:
				h0 += a;
				h1 += b;
				h2 += c;
				h3 += d;
				h4 += e;

			}
		}

		final ByteBuffer padded = pad(src, bytes.length * 8);
		addDigest(padded);
		if (padded.limit() == 128) {
			padded.position(64);
			addDigest(padded.slice());
		}
	}

	/**
	 * Process input bytes and return sha1 digest
	 * @param bytes - message
	 * @return bytes digest
	 */
	public static String getHash(final byte[] bytes) {	
		h0 = 0x67452301;
		h1 = 0xEFCDAB89;
		h2 = 0x98BADCFE;
		h3 = 0x10325476;
		h4 = 0xC3D2E1F0;

		processBytes(bytes);

		return new String(toHexString(h0) + toHexString(h1) + toHexString(h2)
				+ toHexString(h3) + toHexString(h4));
	}

	// Debug printing util methods.
	static final String toHexString(final ByteBuffer bb) {
		final StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bb.limit(); i += 4) {
			if (i % 4 == 0)
				sb.append('\n');
			sb.append(toHexString(bb.getInt(i))).append(' ');
		}
		sb.append('\n');
		return sb.toString();
	}

	static final String toHexString(int x) {
		return padStr(Integer.toHexString(x));
	}

	static final String ZEROS = "00000000";

	static final String padStr(String s) {
		if (s.length() > 8)
			return s.substring(s.length() - 8);
		return ZEROS.substring(s.length()) + s;
	}
}
