/*
 * MIT License
 *
 * Copyright (c) 2020 g4s8
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files
 * (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights * to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package wtf.g4s8.rio.file;

/**
 * Handle all exceptions including unchecked and signal error state to
 * subscriber.
 * @since 0.1
 */
final class ErrorOnException implements Runnable {

    /**
     * Origin runnable.
     */
    private final Runnable runnable;

    /**
     * Subscriber.
     */
    private final ReadSubscriberState<?> sub;

    /**
     * Wrap runnable.
     * @param runnable Runnable to wrap
     * @param sub Subscriber
     */
    ErrorOnException(final Runnable runnable, final ReadSubscriberState<?> sub) {
        this.runnable = runnable;
        this.sub = sub;
    }

    @Override
    @SuppressWarnings("PMD.AvoidCatchingThrowable")
    public void run() {
        try {
            this.runnable.run();
            // @checkstyle IllegalCatchCheck (1 line)
        } catch (final Throwable exx) {
            this.sub.onError(exx);
        }
    }
}
