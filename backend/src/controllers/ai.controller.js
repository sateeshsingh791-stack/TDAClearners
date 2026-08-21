import { generateTutorResponse } from '../services/gemini.service.js';

/**
 * AI Chat Proxy Controller Endpoint
 * Handles POST /api/ai/chat
 */
export const chatProxy = async (req, res, next) => {
  try {
    const {
      history = [],
      userMessage,
      message,
      modelId = 'gemini-2.5-flash',
      academicContext = {}
    } = req.body;

    const promptText = userMessage || message;

    // 1. Validate request
    if (!promptText || typeof promptText !== 'string' || promptText.trim() === '') {
      return res.status(400).json({
        success: false,
        error: { message: 'userMessage or message field is required and must be a non-empty string.' }
      });
    }

    // 2. Call Gemini backend service
    const chatTurn = await generateTutorResponse({
      userMessage: promptText.trim(),
      academicContext,
      history,
      modelId
    });

    // 3. Return sanitized response
    return res.status(200).json({
      success: true,
      data: {
        chatTurn
      }
    });
  } catch (error) {
    console.error('[AI Chat Controller Error]', error.message);
    return res.status(500).json({
      success: false,
      error: { message: 'An error occurred while communicating with the AI Tutor service. Please try again later.' }
    });
  }
};
